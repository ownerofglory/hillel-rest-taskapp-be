package ua.ithillel.hilleltask.exception.handler;

import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import ua.ithillel.hilleltask.exception.InconsistentEntityOperation;
import ua.ithillel.hilleltask.exception.model.ErrorResponse;

@Provider
public class InconsistentOperationExceptionHandler implements ExceptionMapper<InconsistentEntityOperation> {
    @Override
    public Response toResponse(InconsistentEntityOperation e) {
        String message = e.getMessage();
        ErrorResponse errorResponse = new ErrorResponse(message);
        return Response.status(Response.Status.BAD_REQUEST)
                .entity(errorResponse)
                .type(MediaType.APPLICATION_JSON_TYPE)
                .build();
    }
}
