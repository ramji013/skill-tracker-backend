package com.skilltracker.query.api.controllers;

import com.skilltracker.cqrs.core.infrastructure.QueryDispatcher;
import com.skilltracker.cqrs.core.queries.BaseQuery;
import com.skilltracker.query.api.queries.profile.FindProfileByNameQuery;
import com.skilltracker.query.api.queries.profile.FindProfileWithSkillQuery;
import com.skilltracker.profile.models.EmployeeEntity;
import com.skilltracker.query.api.queries.profile.FindAllProfilesQuery;
import com.skilltracker.query.api.queries.profile.FindProfileByIdQuery;

import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/skill-tracker/api/v1/admin")
@RequiredArgsConstructor
public class SkillTrackerLookupController {
  private final Logger logger = Logger.getLogger(SkillTrackerLookupController.class.getName());

  private final QueryDispatcher queryDispatcher;

  @GetMapping()
  @PreAuthorize("hasAuthority('READ_PRIVILEGE')")
  public ResponseEntity<List<EmployeeEntity>> getAllEmployees() {
    try {
      List<EmployeeEntity> employeeEntities = queryDispatcher.send(new FindAllProfilesQuery());
      if (CollectionUtils.isEmpty(employeeEntities)) {
        return ResponseEntity.noContent().build();
      }
      return new ResponseEntity<>(employeeEntities, HttpStatus.OK);
    } catch (Exception e) {
      var safeErrorMessage = "Failed to complete get all employees request!";
      logger.log(Level.SEVERE, safeErrorMessage, e);
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Collections.emptyList());
    }
  }

  @GetMapping("/{criteria}/{criteriaValue}")
  @PreAuthorize("hasAuthority('READ_PRIVILEGE')")
  public ResponseEntity<List<EmployeeEntity>> getEmployeesByCriteria(
      @PathVariable String criteria, @PathVariable String criteriaValue) {
    try {
      Map<String, BaseQuery> queryDispatchMap = new HashMap<>();
      queryDispatchMap.put("id", new FindProfileByIdQuery(criteriaValue));
      queryDispatchMap.put("name", new FindProfileByNameQuery(criteriaValue));
      queryDispatchMap.put("skill", new FindProfileWithSkillQuery(criteriaValue));
      List<EmployeeEntity> employeeEntities;
      if(queryDispatchMap.containsKey(criteria)){
        employeeEntities = queryDispatcher.send(queryDispatchMap.get(criteria));
      }else{
        throw new IllegalStateException(String.format("Unexpected value: %s", criteria));
      }
      return returnResponse(employeeEntities);
    } catch (Exception e) {
      var safeErrorMessage = "Failed to complete get employees by holder request!";
      logger.log(Level.SEVERE, safeErrorMessage, e);
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Collections.emptyList());
    }
  }

  public ResponseEntity<List<EmployeeEntity>> returnResponse(List<EmployeeEntity> employeeEntities){
    return CollectionUtils.isEmpty(employeeEntities) ? ResponseEntity.noContent().build(): ResponseEntity.ok(employeeEntities);
  }
}
