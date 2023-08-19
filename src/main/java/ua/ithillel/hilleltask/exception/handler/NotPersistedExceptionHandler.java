package ua.ithillel.hilleltask.exception.handler;

import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import ua.ithillel.hilleltask.exception.EntityNotSavedException;
import ua.ithillel.hilleltask.exception.model.ErrorResponse;

@Provider
public class NotPersistedExceptionHandler implements ExceptionMapper<EntityNotSavedException> {
    @Override
    public Response toResponse(EntityNotSavedException e) {
        String message = e.getMessage();
        ErrorResponse errorResponse = new ErrorResponse(message);
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity(errorResponse)
                .type(MediaType.APPLICATION_JSON_TYPE)
                .build();
    }
}
