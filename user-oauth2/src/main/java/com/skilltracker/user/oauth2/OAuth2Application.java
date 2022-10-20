package com.skilltracker.user.oauth2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@EnableAuthorizationServer
@RestController
@EnableDiscoveryClient
public class OAuth2Application {

  public static void main(String... args) {
    SpringApplication.run(OAuth2Application.class, args);
  }
}
