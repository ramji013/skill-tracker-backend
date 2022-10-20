package com.skilltracker.cmd.api.controllers;

import com.skilltracker.cmd.api.commands.user.RegisterUserCommand;
import com.skilltracker.cmd.api.commands.user.UpdateUserCommand;
import com.skilltracker.cmd.api.dto.RegisterUserResponse;
import com.skilltracker.common.dto.BaseResponse;
import com.skilltracker.cqrs.core.infrastructure.CommandDispatcher;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
@RequestMapping(path = "/api/v1/user")
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class UserPersistController {
  private final Logger logger = Logger.getLogger(UserPersistController.class.getName());

  private final CommandDispatcher commandDispatcher;

  @PostMapping("/createUser")
  @PreAuthorize("hasAuthority('ADMIN_PRIVILEGE')")
  public ResponseEntity<RegisterUserResponse> createUser(
      @Valid @RequestBody RegisterUserCommand command) {
    var id = UUID.randomUUID().toString();
    command.setId(id);

    try {
      commandDispatcher.send(command);
      return ResponseEntity.status(HttpStatus.CREATED).body(new RegisterUserResponse(id, "User successfully created"));
    } catch (Exception e) {
      var safeErrorMessage = "Error while processing create user request for id - " + id;
      logger.log(Level.SEVERE, safeErrorMessage, e);
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new RegisterUserResponse(id, safeErrorMessage));
    }
  }

  @PutMapping(path = "/updateUser/{id}")
  @PreAuthorize("hasAuthority('ADMIN_PRIVILEGE')")
  public ResponseEntity<BaseResponse> updateUser(
      @PathVariable(value = "id") String id, @Valid @RequestBody UpdateUserCommand command) {
    try {
      command.setId(id);
      commandDispatcher.send(command);
      return ResponseEntity.ok(new BaseResponse("User successfully updated"));
    } catch (Exception e) {
      var safeErrorMessage = "Error while processing update user request for id - " + id;
      logger.log(Level.SEVERE, safeErrorMessage, e);
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new BaseResponse(safeErrorMessage));
    }
  }

}
