package com.skilltracker.cmd.api.dto;

import com.skilltracker.common.dto.BaseResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddProfileResponse extends BaseResponse {
  private String id;

  public AddProfileResponse(String message, String id) {
    super(message);
    this.id = id;
  }
}
