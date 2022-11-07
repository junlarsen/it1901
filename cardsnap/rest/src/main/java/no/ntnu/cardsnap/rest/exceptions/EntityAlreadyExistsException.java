package no.ntnu.cardsnap.rest.exceptions;

public class EntityAlreadyExistsException extends RuntimeException {
    public EntityAlreadyExistsException(final String message) {
        super(message);
    }
}
