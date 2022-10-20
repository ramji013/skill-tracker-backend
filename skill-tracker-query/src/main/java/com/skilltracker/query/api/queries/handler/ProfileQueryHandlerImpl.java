package com.skilltracker.query.api.queries.handler;

import com.skilltracker.cqrs.core.domain.BaseEntity;
import com.skilltracker.profile.models.EmployeeEntity;
import com.skilltracker.profile.models.Skills;
import com.skilltracker.query.api.queries.profile.FindAllProfilesQuery;
import com.skilltracker.query.api.queries.profile.FindProfileByIdQuery;
import com.skilltracker.query.api.queries.profile.FindProfileByNameQuery;
import com.skilltracker.query.api.queries.profile.FindProfileWithSkillQuery;
import com.skilltracker.query.api.repositories.ProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class ProfileQueryHandlerImpl implements ProfileQueryHandler {

  public static final int SKILL_LEVEL = 10;
  private final ProfileRepository profileRepository;

  @Override
  public List<BaseEntity> handle(FindAllProfilesQuery query) {
    var bankAccounts = profileRepository.findAll();
    var bankAccountsList = new ArrayList<BaseEntity>();
    bankAccounts.forEach(bankAccountsList::add);
    return bankAccountsList;
  }

  @Override
  public List<BaseEntity> handle(FindProfileByIdQuery query) {
    return formResponse(profileRepository.findByAssociateId(String.valueOf(query.getId())).stream()
        .collect(Collectors.toList()));
  }

  @Override
  public List<BaseEntity> handle(FindProfileByNameQuery query) {
    return formResponse(profileRepository.findByName(query.getName()));
  }

  @Override
  public List<BaseEntity> handle(FindProfileWithSkillQuery query) {
    if(!query.getSkillName().isBlank()) {
      return formResponse(profileRepository.findBySkill(query.getSkillName().toUpperCase()));
    }
    throw new IllegalArgumentException("Skill Name should not be empty/blank");
  }


  public List<BaseEntity> formResponse(List<BaseEntity> employeeEntityList) {
    Map<String, EmployeeEntity> employeeMap = new HashMap<>();
    employeeEntityList.stream().forEach(e -> {
      EmployeeEntity empEntity = (EmployeeEntity) e;
      if (!employeeMap.containsKey(empEntity.getAssociateId())) {
        EmployeeEntity employeeEntity = new EmployeeEntity();
        employeeEntity.setId(empEntity.getId());
        employeeEntity.setMobile(empEntity.getMobile());
        employeeEntity.setEmail(empEntity.getEmail());
        employeeEntity.setName(empEntity.getName());
        employeeEntity.setAssociateId(empEntity.getAssociateId());
        employeeEntity.setCreatedAt(empEntity.getCreatedAt());
        employeeEntity.setModifiedAt(empEntity.getModifiedAt());
        List<Skills> skillsList = new ArrayList<>();
        skillsList.add(Skills.builder().skill(empEntity.getSkill()).skillLevel(empEntity.getSkillLevel()).build());
        employeeEntity.setSkills(skillsList);
        employeeMap.put(empEntity.getAssociateId(), employeeEntity);
      } else {
        EmployeeEntity employeeEntity = employeeMap.get(empEntity.getAssociateId());
        employeeEntity.getSkills().add(Skills.builder().skill(empEntity.getSkill()).skillLevel(empEntity.getSkillLevel()).build());
      }
    });
    List<BaseEntity> employeeEntities = new ArrayList<>();
    employeeEntities.addAll(employeeMap.values());
    return employeeEntities;
  }

}


