package com.skilltracker.user.models;

import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;

@Getter
public enum RoleEntity implements GrantedAuthority {
  READ_PRIVILEGE,
  WRITE_PRIVILEGE,
  ADMIN_PRIVILEGE;

  @Override
  public String getAuthority() {
    return name();
  }
}
