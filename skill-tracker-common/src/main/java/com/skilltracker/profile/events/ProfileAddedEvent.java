package com.skilltracker.profile.events;

import com.skilltracker.cqrs.core.events.BaseEvent;
import com.skilltracker.profile.models.Skills;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder(toBuilder = true)
public class ProfileAddedEvent extends BaseEvent {
  private String name;
  private String email;
  private String mobile;
  private int skillLevel;
  private String skill;
  private String associateId;
  private List<Skills> skills;
}
