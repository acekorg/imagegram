package com.aleksandar.imagegram.exception;

import com.aleksandar.imagegram.api.model.Error;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.OffsetDateTime;

/**
 * Global exception handler.
 * Logs the exception and returns to the user nice json response that states that error has occured.
 * Example:
 *    {
 *     "timestamp": "2022-10-31T17:44:23.889898+01:00",
 *     "message": "An error has occurred."
 *    }
 */
@ControllerAdvice
public class ImagegramExceptionHandler {

  Logger logger = LoggerFactory.getLogger(ImagegramExceptionHandler.class);

  /**
   * Handle all runtime errors.
   * Log the exception and return error message.
   */
  @ExceptionHandler(RuntimeException.class)
  public ResponseEntity<Error> handleImagegramException(RuntimeException exception) {

    logger.error("An error has occured", exception);

    HttpHeaders httpHeaders = new HttpHeaders();
    httpHeaders.setContentType(MediaType.APPLICATION_PROBLEM_JSON);

    Error error = new Error()
        .timestamp(OffsetDateTime.now())
        .message("An error has occurred.");
    return new ResponseEntity<>(error, httpHeaders, HttpStatus.INTERNAL_SERVER_ERROR);
  }
}
