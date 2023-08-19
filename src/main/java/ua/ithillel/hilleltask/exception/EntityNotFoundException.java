package ua.ithillel.hilleltask.exception;

import jakarta.ws.rs.WebApplicationException;

public class EntityNotFoundException extends WebApplicationException {
    public EntityNotFoundException(String message) {
        super(message);
    }
}
