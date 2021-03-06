package discovery.rest.mapper;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import discovery.NenhumaInstanciaEncontradaException;

@Provider
public class NenhumaInstanciaEncontradaMapper implements ExceptionMapper<NenhumaInstanciaEncontradaException> {
    @Override
    public Response toResponse(NenhumaInstanciaEncontradaException exception) {
        return Response.status(Response.Status.SERVICE_UNAVAILABLE).entity("Servico indisponivel no momento, tente mais tarde").type(MediaType.TEXT_PLAIN_TYPE).build();
    }
}
