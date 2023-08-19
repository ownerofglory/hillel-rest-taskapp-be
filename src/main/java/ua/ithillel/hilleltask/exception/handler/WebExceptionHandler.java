package ua.ithillel.hilleltask.exception.handler;

import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import ua.ithillel.hilleltask.exception.model.ErrorResponse;

@Provider
public class WebExceptionHandler implements ExceptionMapper<WebApplicationException> {
    @Override
    public Response toResponse(WebApplicationException e) {
        String message = "Generic error: " + e.getMessage();
        ErrorResponse errorResponse = new ErrorResponse(message);
        return Response.status(e.getResponse().getStatus())
                .entity(errorResponse)
                .type(MediaType.APPLICATION_JSON_TYPE)
                .build();
    }
}
