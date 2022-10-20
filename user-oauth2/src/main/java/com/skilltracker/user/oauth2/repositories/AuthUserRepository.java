package com.skilltracker.user.oauth2.repositories;

import com.skilltracker.cqrs.core.events.EventModel;
import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthUserRepository extends MongoRepository<EventModel, String> {
  @Query("{'eventData.user.username': ?0}")
  List<EventModel> findByUsername(String username);
}
