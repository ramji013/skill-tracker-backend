package com.skilltracker.cqrs.core.handlers;

import com.skilltracker.cqrs.core.domain.AggregateRoot;

public interface EventSourcingHandler<T> {
  void save(AggregateRoot aggregate);

  T getById(String id);

  void republishEvents();
}
