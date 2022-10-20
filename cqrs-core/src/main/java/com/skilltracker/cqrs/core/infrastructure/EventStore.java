package com.skilltracker.cqrs.core.infrastructure;

import com.skilltracker.cqrs.core.events.BaseEvent;

import java.util.List;

public interface EventStore {
  void saveEvents(
          String aggregateId, Iterable<BaseEvent> events, int expectedVersion, String aggregateType);

  List<BaseEvent> getEvents(String aggregateId);

  List<String> getAggregateIds();
}
