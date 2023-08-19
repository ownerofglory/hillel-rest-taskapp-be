package ua.ithillel.hilleltask.exception;

import jakarta.ws.rs.WebApplicationException;

public class EntityNotSavedException extends WebApplicationException {
    public EntityNotSavedException(String message) {
        super(message);
    }
}
