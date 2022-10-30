package com.aleksandar.imagegram.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Spring security configuration.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests().anyRequest().authenticated()
                .and()
                .httpBasic();

        return http.build();
    }

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
