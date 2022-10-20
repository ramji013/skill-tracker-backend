package com.skilltracker.cmd.api.controllers;

import com.skilltracker.cmd.api.commands.user.RegisterUserCommand;
import com.skilltracker.cmd.api.dto.RegisterUserResponse;
import com.skilltracker.cqrs.core.infrastructure.CommandDispatcher;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/signup")
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class RegisterUserController {
  private final Logger logger = Logger.getLogger(RegisterUserController.class.getName());

  private final CommandDispatcher commandDispatcher;

  @PostMapping("/registerUser")
  public ResponseEntity<RegisterUserResponse> registerUser(
      @RequestBody RegisterUserCommand command) {
    var id = UUID.randomUUID().toString();
    command.setId(id);

    try {
      commandDispatcher.send(command);
      return ResponseEntity.status(HttpStatus.CREATED).body(new RegisterUserResponse(id, "User successfully registered!"));
    } catch (Exception e) {
      var safeErrorMessage = "Error while processing register user request for id - " + id;
      logger.log(Level.SEVERE, safeErrorMessage, e);
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new RegisterUserResponse(id, safeErrorMessage));
    }
  }
}
