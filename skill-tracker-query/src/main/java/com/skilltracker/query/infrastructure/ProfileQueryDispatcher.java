package com.skilltracker.query.infrastructure;

import com.skilltracker.cqrs.core.domain.BaseEntity;
import com.skilltracker.cqrs.core.infrastructure.QueryDispatcher;
import com.skilltracker.cqrs.core.queries.BaseQuery;
import com.skilltracker.cqrs.core.queries.QueryHandlerMethod;
import com.skilltracker.exception.BusinessException;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

@Service
public class ProfileQueryDispatcher implements QueryDispatcher {
  private final Map<Class<? extends BaseQuery>, List<QueryHandlerMethod>> routes = new HashMap<>();

  @Override
  public <T extends BaseQuery> void registerHandler(Class<T> type, QueryHandlerMethod<T> handler) {
    var handlers = routes.computeIfAbsent(type, c -> new LinkedList<>());
    handlers.add(handler);
  }

  @Override
  public <U extends BaseEntity> List<U> send(BaseQuery query) {
    var handlers = routes.get(query.getClass());
    if (CollectionUtils.isEmpty(handlers)) {
      throw new BusinessException("No query handler was registered!");
    }
    if (handlers.size() > 1) {
      throw new BusinessException("Cannot send query to more than one handler!");
    }
    return handlers.get(0).handle(query);
  }
}
