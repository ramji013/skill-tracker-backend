package com.skilltracker.query.api.queries.user;

import com.skilltracker.cqrs.core.queries.BaseQuery;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FindUserByNameQuery implements BaseQuery {
  private String username;
}
