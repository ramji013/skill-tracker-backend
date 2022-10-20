package com.skilltracker.cmd.api.commands.handlers;

import com.skilltracker.cmd.api.commands.skilltracker.EmployeeProfileCommand;
import com.skilltracker.cmd.api.commands.skilltracker.UpdateEmployeeProfileCommand;
import com.skilltracker.cmd.domain.SkillTrackerAggregate;
import com.skilltracker.cmd.api.commands.skilltracker.RestoreReadDbCommand;
import com.skilltracker.cqrs.core.handlers.EventSourcingHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class SkillTrackerCommandHandlerImpl implements SkillTrackerCommandHandler {

  private final EventSourcingHandler<SkillTrackerAggregate> eventSourcingHandler;

  @Override
  public void handle(EmployeeProfileCommand command) {
    var aggregate = new SkillTrackerAggregate(command);
    eventSourcingHandler.save(aggregate);
  }

  @Override
  public void handle(UpdateEmployeeProfileCommand command) {
    var aggregate = eventSourcingHandler.getById(command.getId());
    aggregate.updateEmployee(command);
    eventSourcingHandler.save((aggregate));
  }

  @Override
  public void handle(RestoreReadDbCommand command) {
    eventSourcingHandler.republishEvents();
  }
}
