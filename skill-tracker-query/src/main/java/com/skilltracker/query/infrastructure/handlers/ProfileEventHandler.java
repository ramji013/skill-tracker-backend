package com.skilltracker.query.infrastructure.handlers;

import com.skilltracker.profile.events.ProfileAddedEvent;
import com.skilltracker.profile.events.ProfileUpdatedEvent;

public interface ProfileEventHandler {
  void on(ProfileAddedEvent event);

  void on(ProfileUpdatedEvent event);

}
