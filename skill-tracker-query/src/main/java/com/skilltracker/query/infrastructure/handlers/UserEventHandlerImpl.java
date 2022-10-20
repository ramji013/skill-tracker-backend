package com.skilltracker.query.infrastructure.handlers;

import com.skilltracker.exception.BusinessException;
import com.skilltracker.user.events.UserUpdatedEvent;
import com.skilltracker.user.models.UserEntity;
import com.skilltracker.query.api.repositories.UserRepository;
import com.skilltracker.user.events.UserRegisteredEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class UserEventHandlerImpl implements UserEventHandler {

  private final UserRepository userRepository;

  @Override
  public void on(UserRegisteredEvent event) {
    var eventUser = event.getUser();
    var userEntity =
        UserEntity.builder()
            .id(event.getId())
            .name(eventUser.getName())
            .associateId(eventUser.getAssociateId())
            .emailAddress(eventUser.getEmailAddress())
            .mobileNumber(eventUser.getMobileNumber())
            .username(eventUser.getUsername())
            .password(eventUser.getPassword())
            .build();
    userRepository.save(userEntity);
  }

  @Override
  public void on(UserUpdatedEvent event) {
    var currentUser = userRepository.findById(event.getId()).orElseThrow(BusinessException::new);

    var eventUser = event.getUser();

    currentUser.setName(eventUser.getName());
    currentUser.setAssociateId(eventUser.getAssociateId());
    currentUser.setEmailAddress(eventUser.getEmailAddress());
    currentUser.setMobileNumber(eventUser.getMobileNumber());
    currentUser.setUsername(eventUser.getUsername());
    currentUser.setPassword(eventUser.getPassword());
    userRepository.save(currentUser);
  }

}
