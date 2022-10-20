package com.skilltracker.cqrs.core.producers;

import com.skilltracker.cqrs.core.events.BaseEvent;

public interface EventProducer {
  void produce(String topic, BaseEvent event);
}
