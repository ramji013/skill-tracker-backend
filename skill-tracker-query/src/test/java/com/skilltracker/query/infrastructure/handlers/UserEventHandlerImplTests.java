package com.skilltracker.query.infrastructure.handlers;

import com.skilltracker.user.events.UserUpdatedEvent;
import com.skilltracker.query.api.repositories.UserRepository;
import com.skilltracker.user.events.UserRegisteredEvent;
import com.skilltracker.user.models.UserEntity;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
class UserEventHandlerImplTests {

  @InjectMocks UserEventHandlerImpl eventHandler;

  @Mock private UserRepository repository;

  @Test
  void onUserRegisteredEventTest() {
    Mockito.when(this.repository.save(ArgumentMatchers.any()))
        .thenReturn(Mockito.any(UserEntity.class));
    eventHandler.on(UserRegisteredEvent.builder().user(UserEntity.builder().build()).build());
    Mockito.verify(repository, Mockito.times(1)).save(UserEntity.builder().build());
  }

  @Test
  void onUserUpdatedEventTest() {
    UserEntity entity = Mockito.mock(UserEntity.class);
    Mockito.when(this.repository.findById(ArgumentMatchers.any()))
        .thenReturn(Optional.ofNullable(entity));

    Mockito.when(this.repository.save(ArgumentMatchers.any()))
        .thenReturn(Mockito.any(UserEntity.class));
    eventHandler.on(UserUpdatedEvent.builder().user(UserEntity.builder().build()).build());
    Mockito.verify(repository, Mockito.times(1)).findById(null);
    Mockito.verify(repository, Mockito.times(1)).save(entity);
  }

}
