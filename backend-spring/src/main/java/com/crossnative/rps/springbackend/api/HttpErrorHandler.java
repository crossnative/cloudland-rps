package com.crossnative.rps.springbackend.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class HttpErrorHandler {

  @ExceptionHandler(IllegalArgumentException.class)
  public ResponseEntity<Object> handleIllegalArgumentException(
      final IllegalArgumentException e) {
    return ResponseEntity.badRequest().body(e.getMessage());
  }

}
