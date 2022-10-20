package com.skilltracker.cmd.domain;

import com.skilltracker.cmd.api.commands.user.RegisterUserCommand;
import com.skilltracker.cmd.api.security.PasswordEncoder;
import com.skilltracker.cmd.api.security.PasswordEncoderImpl;
import com.skilltracker.cmd.api.commands.user.UpdateUserCommand;
import com.skilltracker.cqrs.core.domain.AggregateRoot;
import com.skilltracker.user.events.UserRegisteredEvent;
import com.skilltracker.user.events.UserUpdatedEvent;
import com.skilltracker.user.models.UserEntity;
import java.util.UUID;
import lombok.Data;

@Data
public class UserAggregate extends AggregateRoot {
  private String id;
  private UserEntity user;

  private final PasswordEncoder passwordEncoder;

  public UserAggregate() {
    passwordEncoder = new PasswordEncoderImpl();
  }

  public UserAggregate(RegisterUserCommand command) {
    var newUser = command.getUser();
    newUser.setId(command.getId());
    var password = command.getUser().getPassword();
    passwordEncoder = new PasswordEncoderImpl();
    var hashedPassword = passwordEncoder.hashPassword(password);
    newUser.setPassword(hashedPassword);
    raiseEvent(UserRegisteredEvent.builder().id(command.getId()).user(newUser).build());
  }

  public void apply(UserRegisteredEvent event) {
    this.id = event.getId();
    this.user = event.getUser();
  }

  public void updateUser(UpdateUserCommand command) {
    var updatedUser = command.getUser();
    updatedUser.setId(command.getId());
    var password = updatedUser.getPassword();
    var hashedPassword = passwordEncoder.hashPassword(password);
    updatedUser.setPassword(hashedPassword);

    var event =
        UserUpdatedEvent.builder().id(UUID.randomUUID().toString()).user(updatedUser).build();
    raiseEvent(event);
  }

  public void apply(UserUpdatedEvent event) {
    this.id = event.getId();
    this.user = event.getUser();
  }

}
