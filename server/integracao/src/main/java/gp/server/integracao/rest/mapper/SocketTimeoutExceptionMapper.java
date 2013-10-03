package gp.server.integracao.rest.mapper;

import javax.ws.rs.ext.Provider;
import java.net.SocketTimeoutException;

@Provider
public class SocketTimeoutExceptionMapper extends AbstractTimeoutExceptionMapper<SocketTimeoutException> {
}
