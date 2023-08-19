package ua.ithillel.hilleltask.exception;

import jakarta.ws.rs.WebApplicationException;

public class EntityNotDeletedException extends WebApplicationException {
    public EntityNotDeletedException(String message) {
        super(message);
    }
}
