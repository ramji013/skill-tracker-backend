package com.skilltracker.query.infrastructure.handlers;

import com.skilltracker.exception.BusinessException;
import com.skilltracker.profile.events.ProfileAddedEvent;
import com.skilltracker.profile.events.ProfileUpdatedEvent;
import com.skilltracker.profile.models.EmployeeEntity;
import com.skilltracker.query.api.repositories.ProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class ProfileEventHandlerImpl implements ProfileEventHandler {
  private final ProfileRepository profileRepository;

  @Override
  public void on(ProfileAddedEvent event) {
    Optional.ofNullable(event.getSkills()).orElse(Collections.emptyList()).stream().forEach(skill -> {
      EmployeeEntity employee = new EmployeeEntity();
      employee.setSkill(skill.getSkill());
      employee.setSkillLevel(skill.getSkillLevel());
      employee.setId(event.getId());
      employee.setEmail(event.getEmail());
      employee.setMobile(event.getMobile());
      employee.setName(employee.getName());
      employee.setAssociateId(employee.getAssociateId());
      employee.setCreatedAt(new Date());
      employee.setModifiedAt(new Date());
      profileRepository.save(employee);
    });

  }

  @Override
  public void on(ProfileUpdatedEvent event) {
    Optional.ofNullable(event.getSkills()).orElse(Collections.emptyList()).stream().forEach(skill -> {
      var employee = profileRepository.findById(event.getId()).orElseThrow(BusinessException::new);
      employee.setName(event.getName());
      employee.setEmail(event.getEmail());
      employee.setMobile(event.getMobile());
      employee.setSkillLevel(skill.getSkillLevel());
      employee.setModifiedAt(new Date());
      profileRepository.save(employee);
    });
  }
}
