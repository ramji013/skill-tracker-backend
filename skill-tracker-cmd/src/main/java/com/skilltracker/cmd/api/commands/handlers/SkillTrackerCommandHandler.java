package com.skilltracker.cmd.api.commands.handlers;

import com.skilltracker.cmd.api.commands.skilltracker.EmployeeProfileCommand;
import com.skilltracker.cmd.api.commands.skilltracker.UpdateEmployeeProfileCommand;
import com.skilltracker.cmd.api.commands.skilltracker.RestoreReadDbCommand;

public interface SkillTrackerCommandHandler {
  void handle(EmployeeProfileCommand command);

  void handle(UpdateEmployeeProfileCommand command);

  void handle(RestoreReadDbCommand command);
}
