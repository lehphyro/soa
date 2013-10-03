package gp.server.integracao.rest.mapper;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

abstract class AbstractTimeoutExceptionMapper<E extends Throwable> implements ExceptionMapper<E> {

    private static final int GATEWAY_TIMEOUT = 504;

    @Override
    public Response toResponse(E exception) {
        return Response.status(GATEWAY_TIMEOUT).entity("Timeout na execucao da requisicao, tente mais tarde").type(MediaType.TEXT_PLAIN_TYPE).build();
    }
}
