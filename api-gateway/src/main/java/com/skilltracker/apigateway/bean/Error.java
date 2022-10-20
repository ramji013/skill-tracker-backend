package com.skilltracker.apigateway.bean;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

/**
 * it will tell error details
 */
@Builder
public class Error {

  @JsonProperty("errorId")
  private String errorId;

  @JsonProperty("message")
  private String message;

}

