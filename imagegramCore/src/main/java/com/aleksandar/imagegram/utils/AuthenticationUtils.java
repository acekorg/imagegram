package com.aleksandar.imagegram.utils;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

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
    UserDetails principal = (UserDetails) authentication.getPrincipal();
    return principal.getUsername();
  }
}
