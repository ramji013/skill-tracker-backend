package com.skilltracker.query.api.repositories;

import com.skilltracker.user.models.UserEntity;

import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<UserEntity, String> {

  List<UserEntity> findByName(String name);
}
