package server.integracao;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixThreadPoolKey;
import com.sun.jersey.api.client.Client;

import discovery.DescobridorServico;

public abstract class ServiceCommand<T> extends HystrixCommand<T> {

    protected final Client client;
    protected final DescobridorServico descobridorServico;

    protected ServiceCommand(HystrixCommandGroupKey groupKey, HystrixThreadPoolKey poolKey, Client client, DescobridorServico descobridorServico) {
        super(Setter.withGroupKey(groupKey).andThreadPoolKey(poolKey));
        this.client = client;
        this.descobridorServico = descobridorServico;
    }
}
