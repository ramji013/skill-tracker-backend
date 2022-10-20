package com.skilltracker.cmd;

import com.skilltracker.cmd.api.commands.handlers.SkillTrackerCommandHandler;
import com.skilltracker.cmd.api.commands.handlers.UserCommandHandler;
import com.skilltracker.cmd.api.commands.skilltracker.EmployeeProfileCommand;
import com.skilltracker.cmd.api.commands.skilltracker.RestoreReadDbCommand;
import com.skilltracker.cmd.api.commands.skilltracker.UpdateEmployeeProfileCommand;
import com.skilltracker.cmd.api.commands.user.RegisterUserCommand;
import com.skilltracker.cmd.api.commands.user.UpdateUserCommand;
import com.skilltracker.cqrs.core.infrastructure.CommandDispatcher;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

import javax.annotation.PostConstruct;

@SpringBootApplication
@EnableResourceServer
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class})
@RequiredArgsConstructor
@EnableDiscoveryClient
public class SkillTrackerCmdApplication {

  private final CommandDispatcher commandDispatcher;
  private final SkillTrackerCommandHandler skillTrackerCommandHandler;
  private final UserCommandHandler userCommandHandler;

  public static void main(String... args) {
    SpringApplication.run(SkillTrackerCmdApplication.class, args);
  }

  @PostConstruct
  public void registerHandlers() {
    commandDispatcher.registerHandler(
        EmployeeProfileCommand.class, skillTrackerCommandHandler::handle);
    commandDispatcher.registerHandler(
        UpdateEmployeeProfileCommand.class, skillTrackerCommandHandler::handle);

    commandDispatcher.registerHandler(
        RestoreReadDbCommand.class, skillTrackerCommandHandler::handle);
    commandDispatcher.registerHandler(RegisterUserCommand.class, userCommandHandler::handle);
    commandDispatcher.registerHandler(UpdateUserCommand.class, userCommandHandler::handle);
  }
}
