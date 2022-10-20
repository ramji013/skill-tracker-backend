package com.skilltracker.query;

import com.skilltracker.cqrs.core.infrastructure.QueryDispatcher;
import com.skilltracker.query.api.queries.handler.UserQueryHandler;
import com.skilltracker.query.api.queries.profile.FindAllProfilesQuery;
import com.skilltracker.query.api.queries.profile.FindProfileByIdQuery;
import com.skilltracker.query.api.queries.profile.FindProfileByNameQuery;
import com.skilltracker.query.api.queries.profile.FindProfileWithSkillQuery;
import com.skilltracker.query.api.queries.user.FindAllUsersQuery;
import com.skilltracker.query.api.queries.handler.ProfileQueryHandler;
import com.skilltracker.query.api.queries.user.FindUserByIdQuery;
import com.skilltracker.query.api.queries.user.FindUserByNameQuery;
import javax.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

@SpringBootApplication
@EnableResourceServer
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EntityScan(
    basePackages = {"com.skilltracker"})
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@EnableDiscoveryClient
public class SkillTrackerQueryApplication {

  private final QueryDispatcher queryDispatcher;

  private final ProfileQueryHandler profileQueryHandler;
  private final UserQueryHandler userQueryHandler;

  public static void main(String... args) {
    SpringApplication.run(SkillTrackerQueryApplication.class, args);
  }

  @PostConstruct
  public void registerHandlers() {
    queryDispatcher.registerHandler(FindAllProfilesQuery.class, profileQueryHandler::handle);
    queryDispatcher.registerHandler(FindProfileByIdQuery.class, profileQueryHandler::handle);
    queryDispatcher.registerHandler(FindProfileByNameQuery.class, profileQueryHandler::handle);
    queryDispatcher.registerHandler(FindProfileWithSkillQuery.class, profileQueryHandler::handle);

    queryDispatcher.registerHandler(FindUserByIdQuery.class, userQueryHandler::handle);
    queryDispatcher.registerHandler(FindAllUsersQuery.class, userQueryHandler::handle);
    queryDispatcher.registerHandler(FindUserByNameQuery.class, userQueryHandler::handle);
  }
}
