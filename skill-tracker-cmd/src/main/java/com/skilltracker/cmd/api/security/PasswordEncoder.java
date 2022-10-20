package com.skilltracker.cmd.api.security;

public interface PasswordEncoder {
  String hashPassword(String password);
}
