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

@ControllerAdvice
public class ImagegramExceptionHandler {

  Logger logger = LoggerFactory.getLogger(ImagegramExceptionHandler.class);

  @ExceptionHandler(RuntimeException.class)
  public ResponseEntity<Error> handleImagegramException(RuntimeException exception) {

    logger.error(exception.getMessage());
    exception.printStackTrace();

    HttpHeaders httpHeaders = new HttpHeaders();
    httpHeaders.setContentType(MediaType.APPLICATION_PROBLEM_JSON);

    Error error = new Error()
        .timestamp(OffsetDateTime.now())
        .message("An error has occurred.");
    return new ResponseEntity<>(error, httpHeaders, HttpStatus.INTERNAL_SERVER_ERROR);
  }
}
