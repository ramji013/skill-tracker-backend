package com.skilltracker.query.infrastructure.consumers;

import com.skilltracker.profile.events.ProfileAddedEvent;
import com.skilltracker.profile.events.ProfileUpdatedEvent;
import com.skilltracker.query.infrastructure.handlers.ProfileEventHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class ProfileEventConsumerImpl implements ProfileEventConsumer {
  private final ProfileEventHandler profileEventHandler;

  @KafkaListener(topics = "ProfileAddedEvent", groupId = "${spring.kafka.consumer.group-id}")
  @Override
  public void consume(@Payload ProfileAddedEvent event, Acknowledgment ack) {
    this.profileEventHandler.on(event);
    ack.acknowledge();
  }

  @KafkaListener(topics = "ProfileUpdatedEvent", groupId = "${spring.kafka.consumer.group-id}")
  @Override
  public void consume(@Payload ProfileUpdatedEvent event, Acknowledgment ack) {
    this.profileEventHandler.on(event);
    ack.acknowledge();
  }

}
