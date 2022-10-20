package com.skilltracker.cmd.api.commands.handlers;

import com.skilltracker.cmd.api.commands.user.RegisterUserCommand;
import com.skilltracker.cmd.api.commands.user.UpdateUserCommand;
import com.skilltracker.cmd.domain.UserAggregate;
import com.skilltracker.cqrs.core.handlers.EventSourcingHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class UserSkillTrackerCommandHandlerImpl implements UserCommandHandler {
  private final EventSourcingHandler<UserAggregate> eventSourcingHandler;

  @Override
  public void handle(RegisterUserCommand command) {
    var aggregate = new UserAggregate(command);

    eventSourcingHandler.save(aggregate);
  }

  @Override
  public void handle(UpdateUserCommand command) {
    var aggregate = eventSourcingHandler.getById(command.getId());
    aggregate.updateUser(command);

    eventSourcingHandler.save((aggregate));
  }

}
