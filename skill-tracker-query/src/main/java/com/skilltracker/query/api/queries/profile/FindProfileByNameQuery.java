package com.skilltracker.query.api.queries.profile;

import com.skilltracker.cqrs.core.queries.BaseQuery;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FindProfileByNameQuery implements BaseQuery {
  private String name;
}
