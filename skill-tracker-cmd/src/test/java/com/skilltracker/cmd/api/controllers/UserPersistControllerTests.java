package com.skilltracker.cmd.api.controllers;

import com.skilltracker.cmd.api.commands.user.RegisterUserCommand;
import com.skilltracker.cmd.api.commands.user.UpdateUserCommand;
import com.skilltracker.cqrs.core.infrastructure.CommandDispatcher;
import com.skilltracker.exception.BusinessException;
import com.skilltracker.user.models.UserEntity;
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
class UserPersistControllerTests {

  @InjectMocks UserPersistController controller;

  @Mock private CommandDispatcher commandDispatcher;

  @Test
  void registerUserTest() {
    Mockito.doNothing().when(this.commandDispatcher).send(ArgumentMatchers.any());
    var responseDto =
        controller.createUser(
            RegisterUserCommand.builder().user(UserEntity.builder().build()).build());
    Assertions.assertEquals(HttpStatus.CREATED.value(), responseDto.getStatusCodeValue());
  }

  @Test
  void registerUserExceptionTest() {
    Mockito.doThrow(new BusinessException("Unexpected value: "))
        .when(this.commandDispatcher)
        .send(ArgumentMatchers.any());
    var responseDto =
        controller.createUser(
            RegisterUserCommand.builder().user(UserEntity.builder().build()).build());
    Assertions.assertEquals(
        HttpStatus.INTERNAL_SERVER_ERROR.value(), responseDto.getStatusCodeValue());
  }

  @Test
  void updateUserTest() {
    Mockito.doNothing().when(this.commandDispatcher).send(ArgumentMatchers.any());
    var responseDto =
        controller.updateUser(
            "", UpdateUserCommand.builder().user(UserEntity.builder().build()).build());
    Assertions.assertEquals(HttpStatus.OK.value(), responseDto.getStatusCodeValue());
  }

  @Test
  void updateUserExceptionTest() {
    Mockito.doThrow(new RuntimeException("Unexpected value: "))
        .when(this.commandDispatcher)
        .send(ArgumentMatchers.any());
    var responseDto =
        controller.updateUser(
            "", UpdateUserCommand.builder().user(UserEntity.builder().build()).build());
    Assertions.assertEquals(
        HttpStatus.INTERNAL_SERVER_ERROR.value(), responseDto.getStatusCodeValue());
  }

}
