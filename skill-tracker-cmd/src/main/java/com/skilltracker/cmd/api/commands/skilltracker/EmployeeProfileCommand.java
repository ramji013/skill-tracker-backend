package com.skilltracker.cmd.api.commands.skilltracker;

import com.skilltracker.cqrs.core.commands.BaseCommand;
import com.skilltracker.profile.models.Skills;
import lombok.Data;

import java.util.List;

@Data
public class EmployeeProfileCommand extends BaseCommand {
  private String name;
  private String email;
  private String mobile;
  private int skillLevel;
  private String skill;
  private String associateId;
  private List<Skills> skills;
}
