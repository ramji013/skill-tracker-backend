package com.skilltracker.query.infrastructure.consumers;

import com.skilltracker.user.events.UserRegisteredEvent;
import com.skilltracker.user.events.UserUpdatedEvent;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.messaging.handler.annotation.Payload;

public interface UserEventConsumer {
  void consume(@Payload UserRegisteredEvent event, Acknowledgment ack);

  void consume(@Payload UserUpdatedEvent event, Acknowledgment ack);

}
