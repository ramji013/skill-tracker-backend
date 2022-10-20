package com.skilltracker.apigateway.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.Collections;

@Configuration
public class SimpleCorsConfiguration {

  @Bean
  CorsWebFilter corsWebFilter() {
    var corsConfig = new CorsConfiguration();
    corsConfig.setAllowedOrigins(Collections.singletonList("*"));
    corsConfig.setMaxAge(8000L);
    corsConfig.setAllowedMethods(Arrays.asList("OPTIONS", "GET", "POST", "DELETE", "PUT", "PATCH"));
    corsConfig.addAllowedHeader("*");

    var source = new UrlBasedCorsConfigurationSource();
    source.registerCorsConfiguration("/**", corsConfig);

    return new CorsWebFilter(source);
  }
}
