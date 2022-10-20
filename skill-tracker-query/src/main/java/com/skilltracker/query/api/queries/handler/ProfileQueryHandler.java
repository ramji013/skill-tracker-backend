package com.skilltracker.query.api.queries.handler;

import com.skilltracker.cqrs.core.domain.BaseEntity;
import com.skilltracker.query.api.queries.profile.FindAllProfilesQuery;
import com.skilltracker.query.api.queries.profile.FindProfileByIdQuery;
import com.skilltracker.query.api.queries.profile.FindProfileByNameQuery;
import com.skilltracker.query.api.queries.profile.FindProfileWithSkillQuery;

import java.util.List;

public interface ProfileQueryHandler {
  List<BaseEntity> handle(FindAllProfilesQuery query);

  List<BaseEntity> handle(FindProfileByIdQuery query);

  List<BaseEntity> handle(FindProfileByNameQuery query);

  List<BaseEntity> handle(FindProfileWithSkillQuery query);
}
