package gp.server.integracao.rest.mapper;

import java.util.concurrent.ExecutionException;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import javax.ws.rs.ext.Providers;

@Provider
public class ExecutionExceptionMapper implements ExceptionMapper<ExecutionException> {

    @SuppressWarnings("WeakerAccess")
    @Context
    Providers providers;

    @Override
    @SuppressWarnings("unchecked")
    public Response toResponse(ExecutionException exception) {
        ExceptionMapper mapper = providers.getExceptionMapper(exception.getCause().getClass());
        if (mapper == null) {
            throw new IllegalStateException("Nenhum exception mapper encontrado para exception", exception);
        }
        return mapper.toResponse(exception.getCause());
    }
}
