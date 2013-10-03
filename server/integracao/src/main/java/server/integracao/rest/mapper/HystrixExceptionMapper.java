package server.integracao.rest.mapper;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import javax.ws.rs.ext.Providers;

import com.netflix.hystrix.exception.HystrixRuntimeException;

@Provider
public class HystrixExceptionMapper implements ExceptionMapper<HystrixRuntimeException> {

	@Context
	Providers providers;

	@Override
	@SuppressWarnings("unchecked")
	public Response toResponse(HystrixRuntimeException exception) {
		@SuppressWarnings("rawtypes")
		ExceptionMapper mapper = providers.getExceptionMapper(exception.getCause().getClass());
		if (mapper == null) {
			throw new IllegalStateException("Nenhum exception mapper encontrado para exception", exception);
		}
		return mapper.toResponse(exception.getCause());
	}
}
