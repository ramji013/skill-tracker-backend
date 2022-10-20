package com.skilltracker.cmd.api.controllers;

import com.skilltracker.cmd.api.commands.skilltracker.EmployeeProfileCommand;
import com.skilltracker.cmd.api.commands.skilltracker.UpdateEmployeeProfileCommand;
import com.skilltracker.cmd.api.dto.AddProfileResponse;
import com.skilltracker.common.dto.BaseResponse;
import com.skilltracker.cqrs.core.commands.BaseCommand;
import com.skilltracker.cqrs.core.infrastructure.CommandDispatcher;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.text.MessageFormat;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
@RequestMapping(path = "/skill-tracker/api/v1/engineer")
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class SkillTrackerPersistController {
  private final Logger logger = Logger.getLogger(SkillTrackerPersistController.class.getName());

  private final CommandDispatcher commandDispatcher;

  @PostMapping("/add-profile")
  @PreAuthorize("hasAuthority('WRITE_PRIVILEGE')")
  public ResponseEntity<BaseResponse> addProfile(@RequestBody EmployeeProfileCommand command) {
    var id = UUID.randomUUID().toString();
    return persistProfile(id, command, commandDispatcher, logger);
  }

  @PutMapping("/update-profile/{id}")
  @PreAuthorize("hasAuthority('WRITE_PRIVILEGE')")
  public ResponseEntity<BaseResponse> updateProfile(
      @PathVariable String id, @RequestBody UpdateEmployeeProfileCommand command) {
    return persistProfile(id, command, commandDispatcher, logger);
  }

  static ResponseEntity<BaseResponse> persistProfile(
      @PathVariable String id,
      @RequestBody BaseCommand command,
      CommandDispatcher commandDispatcher,
      Logger logger) {
    try {
      command.setId(id);
      commandDispatcher.send(command);
      return new ResponseEntity<>(
          new AddProfileResponse("Profile creation request completed successfully!", id),
          HttpStatus.CREATED);
    } catch (IllegalStateException e) {
      logger.log(Level.WARNING, MessageFormat.format("Client made a bad request - {0}.", e));
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new BaseResponse(e.toString()));
    } catch (Exception e) {
      var safeErrorMessage =
          MessageFormat.format(
              "Error while processing request to add a new profile for id - {0}.", id);
      logger.log(Level.SEVERE, safeErrorMessage, e);
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body( new AddProfileResponse(safeErrorMessage, id));
    }
  }
}
