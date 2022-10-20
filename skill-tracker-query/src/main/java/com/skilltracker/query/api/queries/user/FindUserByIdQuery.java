package com.skilltracker.query.api.queries.user;

import com.skilltracker.cqrs.core.queries.BaseQuery;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FindUserByIdQuery implements BaseQuery {
  private String id;
}
