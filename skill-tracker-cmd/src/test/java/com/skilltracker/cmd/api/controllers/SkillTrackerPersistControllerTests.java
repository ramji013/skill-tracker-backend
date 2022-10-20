package com.skilltracker.cmd.api.controllers;

import com.skilltracker.cmd.api.commands.skilltracker.EmployeeProfileCommand;
import com.skilltracker.cmd.api.commands.skilltracker.UpdateEmployeeProfileCommand;
import com.skilltracker.cqrs.core.infrastructure.CommandDispatcher;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
class SkillTrackerPersistControllerTests {

  @InjectMocks SkillTrackerPersistController controller;

  @Mock private CommandDispatcher commandDispatcher;

  @Test
  void addProfileTest() {
    Mockito.doNothing().when(this.commandDispatcher).send(ArgumentMatchers.any());
    var responseDto = controller.addProfile(new EmployeeProfileCommand());
    Assertions.assertEquals(HttpStatus.CREATED.value(), responseDto.getStatusCodeValue());
  }

  @Test
  void addProfileIllegalStateExceptionTest() {
    Mockito.doThrow(new IllegalStateException("Unexpected value: "))
        .when(this.commandDispatcher)
        .send(ArgumentMatchers.any());
    var responseDto = controller.addProfile(new EmployeeProfileCommand());
    Assertions.assertEquals(HttpStatus.BAD_REQUEST.value(), responseDto.getStatusCodeValue());
  }

  @Test
  void addProfileExceptionTest() {
    Mockito.doThrow(new RuntimeException("Unexpected value: "))
        .when(this.commandDispatcher)
        .send(ArgumentMatchers.any());
    var responseDto = controller.addProfile(new EmployeeProfileCommand());
    Assertions.assertEquals(
        HttpStatus.INTERNAL_SERVER_ERROR.value(), responseDto.getStatusCodeValue());
  }

  @Test
  void updateProfileTest() {
    Mockito.doNothing().when(this.commandDispatcher).send(ArgumentMatchers.any());
    var responseDto = controller.updateProfile("", new UpdateEmployeeProfileCommand());
    Assertions.assertEquals(HttpStatus.CREATED.value(), responseDto.getStatusCodeValue());
  }

}
