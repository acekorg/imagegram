package com.aleksandar.imagegram.security.utils;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * Authentication helper class which provides information from spring security context.
 */
public final class AuthenticationUtils {

  /**
   * Retrieves the logged-in user.
   *
   * @return String representation of the logged-in user.
   */
  public static String getLoggedInUser() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    return authentication.getPrincipal().toString();
  }
}
