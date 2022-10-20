package com.skilltracker.cqrs.core.infrastructure;

import com.skilltracker.cqrs.core.commands.BaseCommand;
import com.skilltracker.cqrs.core.commands.CommandHandlerMethod;

public interface CommandDispatcher {
  <T extends BaseCommand> void registerHandler(Class<T> type, CommandHandlerMethod<T> handler);

  void send(BaseCommand command);
}
