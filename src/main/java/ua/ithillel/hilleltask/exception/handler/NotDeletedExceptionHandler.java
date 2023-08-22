package ua.ithillel.hilleltask.exception.handler;

import jakarta.annotation.Priority;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import org.apache.logging.log4j.LogManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.ithillel.hilleltask.exception.EntityNotDeletedException;
import ua.ithillel.hilleltask.exception.model.ErrorResponse;

@Provider
public class NotDeletedExceptionHandler implements ExceptionMapper<EntityNotDeletedException> {
    private static final Logger LOGGER = LoggerFactory.getLogger(NotDeletedExceptionHandler.class);

    @Override
    public Response toResponse(EntityNotDeletedException e) {
        LOGGER.error("Exception: {} ", e.getMessage());

        String message = e.getMessage();
        ErrorResponse errorResponse = new ErrorResponse(message);
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity(errorResponse)
                .type(MediaType.APPLICATION_JSON_TYPE)
                .build();
    }
}
