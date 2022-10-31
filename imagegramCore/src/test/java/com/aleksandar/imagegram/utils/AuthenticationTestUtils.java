package com.aleksandar.imagegram.utils;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.TransientSecurityContext;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * Authentication test utils.
 */
public final class AuthenticationTestUtils {

  /**
   * Set username as logged in Spring security context.
   * */
  public static void mockSecurityContext() {

    UserDetails userDetails = User
        .withUsername("dummyUser")
        .password("dummyPassword")
        .roles("USER")
        .build();

    UsernamePasswordAuthenticationToken authentication =
        new UsernamePasswordAuthenticationToken(userDetails, null);

    TransientSecurityContext transientSecurityContext = new TransientSecurityContext(authentication);
    SecurityContextHolder.setContext(transientSecurityContext);
  }
}
