package no.ntnu.cardsnap.core;

/**
 * Exception type to throw when an entity unexpectedly does not exist.
 */
public class EntityNotFoundException extends RuntimeException {
  public EntityNotFoundException(String message) {
    super(message);
  }
}
