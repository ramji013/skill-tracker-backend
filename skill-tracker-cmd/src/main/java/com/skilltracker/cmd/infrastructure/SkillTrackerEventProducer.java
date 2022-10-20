package com.skilltracker.cmd.infrastructure;

import com.skilltracker.cqrs.core.events.BaseEvent;
import com.skilltracker.cqrs.core.producers.EventProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class SkillTrackerEventProducer implements EventProducer {

  private final KafkaTemplate<String, Object> kafkaTemplate;

  @Override
  public void produce(String topic, BaseEvent event) {
    this.kafkaTemplate.send(topic, event);
  }
}
