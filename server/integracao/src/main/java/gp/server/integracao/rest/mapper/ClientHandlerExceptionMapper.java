package gp.server.integracao.rest.mapper;

import com.sun.jersey.api.client.ClientHandlerException;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class ClientHandlerExceptionMapper implements ExceptionMapper<ClientHandlerException> {
    @Override
    public Response toResponse(ClientHandlerException exception) {
        return Response.status(Response.Status.SERVICE_UNAVAILABLE).entity("Servico indisponivel no momento, tente mais tarde").type(MediaType.TEXT_PLAIN_TYPE).build();
    }
}
