package com.skilltracker.user.models;

import com.skilltracker.cqrs.core.domain.BaseEntity;
import java.util.List;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@Entity
@Component
public class UserEntity implements BaseEntity {
  @Id private String id;

  private String name;

  private String associateId;

  private String emailAddress;

  private String mobileNumber;

  private String username;

  private String password;

  @ElementCollection private List<RoleEntity> roles;
}
