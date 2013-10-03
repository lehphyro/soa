package gp.server.integracao.rest.mapper;

import javax.ws.rs.ext.Provider;
import java.util.concurrent.TimeoutException;

@Provider
public class TimeoutExceptionMapper extends AbstractTimeoutExceptionMapper<TimeoutException> {
}
