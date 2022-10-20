package com.skilltracker.cmd.api.commands.handlers;

import com.skilltracker.cmd.api.commands.user.RegisterUserCommand;
import com.skilltracker.cmd.api.commands.user.UpdateUserCommand;

public interface UserCommandHandler {
  void handle(RegisterUserCommand command);

  void handle(UpdateUserCommand command);

}
