package com.skilltracker.cmd.domain;

import com.skilltracker.cmd.api.commands.skilltracker.EmployeeProfileCommand;
import com.skilltracker.cmd.api.commands.skilltracker.UpdateEmployeeProfileCommand;
import com.skilltracker.cqrs.core.domain.AggregateRoot;
import com.skilltracker.profile.events.ProfileAddedEvent;
import com.skilltracker.profile.events.ProfileUpdatedEvent;
import com.skilltracker.profile.models.Skills;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class SkillTrackerAggregate extends AggregateRoot {
  private String name;
  private String email;
  private String mobile;
  private String skill;

  private int skillLevel;

  private String associateId;

  private List<Skills> skills;

  public SkillTrackerAggregate(EmployeeProfileCommand command) {
    raiseEvent(
        ProfileAddedEvent.builder()
            .id(command.getId())
            .name(command.getName())
            .email(command.getEmail())
            .mobile(command.getMobile())
            .skill(command.getSkill())
            .skillLevel(command.getSkillLevel())
                .skills(command.getSkills())
                .associateId(command.getAssociateId())
            .build());
  }

  public void apply(ProfileAddedEvent event) {
    this.id = event.getId();
    this.name = event.getName();
    this.email = event.getEmail();
    this.mobile = event.getMobile();
    this.skill = event.getSkill();
    this.skillLevel = event.getSkillLevel();
    this.skills = event.getSkills();
    this.associateId = event.getAssociateId();
  }

  public void updateEmployee(UpdateEmployeeProfileCommand command) {
    raiseEvent(
        ProfileUpdatedEvent.builder()
            .id(command.getId())
            .name(command.getName())
            .email(command.getEmail())
            .mobile(command.getMobile())
                /*.skill(command.getSkill())
                .skillLevel(command.getSkillLevel())*/
                .skills(command.getSkills())
                .associateId(command.getAssociateId())
            .build());
  }

  public void apply(ProfileUpdatedEvent event) {
    this.id = event.getId();
    this.name = event.getName();
    this.email = event.getEmail();
    this.mobile = event.getMobile();
  /*  this.skillLevel = event.getSkillLevel();
    this.skill = event.getSkill();*/
    this.skills = event.getSkills();
    this.associateId = event.getAssociateId();
  }

}
