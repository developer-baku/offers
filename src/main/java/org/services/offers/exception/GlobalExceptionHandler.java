package org.services.offers.exception;

import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.io.IOException;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<String> handleValidationException(MethodArgumentNotValidException ex) {
    log.error("Validation error: {}", ex.getMessage(), ex);
    return ResponseEntity.badRequest().body("Validation error: " + ex.getMessage());
  }

  @ExceptionHandler(EntityNotFoundException.class)
  public ResponseEntity<String> handleEntityNotFoundException(EntityNotFoundException ex) {
    log.warn("Entity not found: {}", ex.getMessage(), ex);
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error: " + ex.getMessage());
  }

  @ExceptionHandler(MethodArgumentTypeMismatchException.class)
  public ResponseEntity<String> handleArgumentTypeMismatch(MethodArgumentTypeMismatchException ex) {
    log.error("Argument type mismatch: {}", ex.getMessage(), ex);
    return ResponseEntity.status(HttpStatus.BAD_REQUEST)
        .body(
            "Invalid argument: "
                + ex.getName()
                + " must be of type "
                + ex.getRequiredType().getSimpleName());
  }

  @ExceptionHandler(IOException.class)
  public ResponseEntity<String> handleIOException(IOException ex) {
    log.error("General IO Error: {}", ex.getMessage(), ex);
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
        .body("General IO Error, Zip file could be missing on Server");
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<String> handleGlobalException(Exception ex) {
    log.error("Unexpected error occurred: {}", ex.getMessage(), ex);
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
        .body("An unexpected error occurred. Please try again later.");
  }
}
