package com.skilltracker.cmd.api.commands.handlers;

import com.skilltracker.cmd.api.commands.user.RegisterUserCommand;
import com.skilltracker.cmd.api.commands.user.UpdateUserCommand;
import com.skilltracker.cmd.domain.UserAggregate;
import com.skilltracker.cqrs.core.handlers.EventSourcingHandler;
import com.skilltracker.user.models.UserEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
class UserTrackerCommandHandlerImplTests {
  @InjectMocks UserSkillTrackerCommandHandlerImpl commandHandler;

  @Mock private EventSourcingHandler<UserAggregate> eventSourcingHandler;

  @Test
  void handleRegisterUserCommandTest() {
    Mockito.doNothing().when(this.eventSourcingHandler).save(ArgumentMatchers.any());
    commandHandler.handle(
        RegisterUserCommand.builder().user(UserEntity.builder().password("").build()).build());
    Mockito.verify(eventSourcingHandler, Mockito.times(1)).save(Mockito.any(UserAggregate.class));
  }

  @Test
  void handleUpdateUserCommandTest() {
    Mockito.when(this.eventSourcingHandler.getById(ArgumentMatchers.any()))
        .thenReturn(new UserAggregate());
    Mockito.doNothing().when(this.eventSourcingHandler).save(ArgumentMatchers.any());
    commandHandler.handle(
        UpdateUserCommand.builder().user(UserEntity.builder().password("").build()).build());
    Mockito.verify(eventSourcingHandler, Mockito.times(1)).save(Mockito.any(UserAggregate.class));
  }

}
