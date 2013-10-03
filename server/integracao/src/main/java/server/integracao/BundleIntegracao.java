package server.integracao;

import server.integracao.rest.mapper.*;

import com.netflix.hystrix.contrib.metrics.eventstream.HystrixMetricsStreamServlet;
import com.yammer.dropwizard.Bundle;
import com.yammer.dropwizard.config.Bootstrap;
import com.yammer.dropwizard.config.Environment;

public class BundleIntegracao implements Bundle {

    @Override
    public void initialize(Bootstrap<?> bootstrap) {
    }

    @Override
    public void run(Environment environment) {
        environment.addServlet(HystrixMetricsStreamServlet.class, "/hystrix.stream");
        environment.addProvider(TimeoutExceptionMapper.class);
        environment.addProvider(SocketTimeoutExceptionMapper.class);
        environment.addProvider(ClientHandlerExceptionMapper.class);
        environment.addProvider(ExecutionExceptionMapper.class);
        environment.addProvider(HystrixExceptionMapper.class);
    }
}
