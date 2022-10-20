package com.skilltracker.query.api.queries.handler;

import com.skilltracker.cqrs.core.domain.BaseEntity;
import com.skilltracker.query.api.queries.user.FindAllUsersQuery;
import com.skilltracker.query.api.queries.user.FindUserByIdQuery;
import com.skilltracker.query.api.queries.user.FindUserByNameQuery;
import java.util.List;

public interface UserQueryHandler {

  List<BaseEntity> handle(FindUserByIdQuery query);

  List<BaseEntity> handle(FindUserByNameQuery query);

  List<BaseEntity> handle(FindAllUsersQuery query);
}
