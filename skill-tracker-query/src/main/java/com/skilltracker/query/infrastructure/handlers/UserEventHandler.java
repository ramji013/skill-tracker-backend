package com.skilltracker.query.infrastructure.handlers;

import com.skilltracker.user.events.UserRegisteredEvent;
import com.skilltracker.user.events.UserUpdatedEvent;

public interface UserEventHandler {
  void on(UserRegisteredEvent event);

  void on(UserUpdatedEvent event);

}
