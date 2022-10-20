package com.skilltracker.query.api.queries.profile;

import com.skilltracker.cqrs.core.queries.BaseQuery;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FindProfileWithSkillQuery implements BaseQuery {
  private String skillName;
}
