package com.skilltracker.query.api.repositories;

import com.skilltracker.cqrs.core.domain.BaseEntity;
import com.skilltracker.profile.models.EmployeeEntity;

import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface ProfileRepository extends CrudRepository<EmployeeEntity, String> {
  List<BaseEntity> findByName(String name);

  List<BaseEntity> findBySkill(String skill);

  List<BaseEntity> findByAssociateId(String associateId);
}
