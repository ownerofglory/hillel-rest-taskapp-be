package ua.ithillel.hilleltask.exception;

import jakarta.ws.rs.WebApplicationException;

public class InconsistentEntityOperation extends WebApplicationException {
    public InconsistentEntityOperation(String message) {
        super(message);
    }
}
