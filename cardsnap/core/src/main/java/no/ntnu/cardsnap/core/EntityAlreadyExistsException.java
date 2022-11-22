package no.ntnu.cardsnap.core;

/**
 * Exception type to throw when an entity already exists.
 */
public class EntityAlreadyExistsException extends RuntimeException {
  public EntityAlreadyExistsException(String message) {
    super(message);
  }
}
