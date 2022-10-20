package com.skilltracker.cqrs.core.infrastructure;

import com.skilltracker.cqrs.core.domain.BaseEntity;
import com.skilltracker.cqrs.core.queries.BaseQuery;
import com.skilltracker.cqrs.core.queries.QueryHandlerMethod;

import java.util.List;

public interface QueryDispatcher {
  <T extends BaseQuery> void registerHandler(Class<T> type, QueryHandlerMethod<T> handler);

  <U extends BaseEntity> List<U> send(BaseQuery query);
}
