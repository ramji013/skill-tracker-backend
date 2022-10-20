package com.skilltracker.user.events;

import com.skilltracker.cqrs.core.events.BaseEvent;
import com.skilltracker.user.models.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder(toBuilder = true)
public class UserRegisteredEvent extends BaseEvent {
  private UserEntity user;
}
