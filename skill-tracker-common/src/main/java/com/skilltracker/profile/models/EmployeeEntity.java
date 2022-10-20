package com.skilltracker.profile.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.skilltracker.cqrs.core.domain.BaseEntity;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@Entity
public class EmployeeEntity implements BaseEntity {
  @Id private String id;

  private String name;

  private String email;

  private String mobile;

  private String associateId;

  @JsonIgnore
  private String skill;

  @JsonIgnore
  private int skillLevel;

  @Temporal(TemporalType.TIMESTAMP)
  private Date createdAt;

  @Temporal(TemporalType.TIMESTAMP)
  private Date modifiedAt;

  @Transient
  private List<Skills> skills;
}
