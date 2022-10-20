package com.skilltracker.query.api.dto;

import com.skilltracker.common.dto.BaseResponse;
import com.skilltracker.profile.models.EmployeeEntity;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class ProfileLookupResponse extends BaseResponse {
  private List<EmployeeEntity> employeeEntities;

  public ProfileLookupResponse(String message) {
    super(message);
  }
}
