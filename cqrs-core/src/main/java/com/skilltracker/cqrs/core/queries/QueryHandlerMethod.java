package com.skilltracker.cqrs.core.queries;

import com.skilltracker.cqrs.core.domain.BaseEntity;

import java.util.List;

@FunctionalInterface
public interface QueryHandlerMethod<T extends BaseQuery> {
  List<BaseEntity> handle(T query);
}
