package com.aleksandar.imagegram.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class InMemoryUsers {

  @Bean
  public InMemoryUserDetailsManager userDetailsService() {

    InMemoryUserDetailsManager inMemoryUserDetailsManager = new InMemoryUserDetailsManager();

    UserDetails user1 = User
        .withUsername("user1")
        .password("{noop}password")
        .roles("USER")
        .build();
    inMemoryUserDetailsManager.createUser(user1);

    UserDetails user2 = User
        .withUsername("user2")
        .password("{noop}password")
        .roles("USER")
        .build();
    inMemoryUserDetailsManager.createUser(user2);

    UserDetails user3 = User
        .withUsername("user3")
        .password("{noop}password")
        .roles("USER")
        .build();
    inMemoryUserDetailsManager.createUser(user3);

    return inMemoryUserDetailsManager;
  }
}
