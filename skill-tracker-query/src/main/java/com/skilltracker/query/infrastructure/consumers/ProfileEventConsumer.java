package com.skilltracker.query.infrastructure.consumers;

import com.skilltracker.profile.events.ProfileAddedEvent;
import com.skilltracker.profile.events.ProfileUpdatedEvent;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.messaging.handler.annotation.Payload;

public interface ProfileEventConsumer {
  void consume(@Payload ProfileUpdatedEvent event, Acknowledgment ack);

  void consume(@Payload ProfileAddedEvent event, Acknowledgment ack);

}
