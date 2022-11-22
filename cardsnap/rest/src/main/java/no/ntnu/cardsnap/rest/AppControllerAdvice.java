package no.ntnu.cardsnap.rest;

import java.io.IOException;
import no.ntnu.cardsnap.core.EntityAlreadyExistsException;
import no.ntnu.cardsnap.core.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Controller Advice for the REST application.
 *
 * <p>This controller advice defines exception mapping, making Spring return
 * proper return HTTP codes when catching various exceptions.
 */
@RestControllerAdvice
public class AppControllerAdvice {
  @ExceptionHandler(IOException.class)
  public ResponseEntity<?> onIoException(IOException exception) {
    return new ResponseEntity<>(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
  }

  @ExceptionHandler(IllegalArgumentException.class)
  public ResponseEntity<?> onIllegalArgumentException(IllegalArgumentException exception) {
    return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(EntityNotFoundException.class)
  public ResponseEntity<?> onEntityNotFoundException(EntityNotFoundException exception) {
    return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(EntityAlreadyExistsException.class)
  public ResponseEntity<?> onEntityAlreadyExistsException(EntityAlreadyExistsException exception) {
    return new ResponseEntity<>(exception.getMessage(), HttpStatus.CONFLICT);
  }
}
