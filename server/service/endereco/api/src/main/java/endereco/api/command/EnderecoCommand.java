package endereco.api.command;

import server.integracao.ServiceCommand;

import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixThreadPoolKey;
import com.sun.jersey.api.client.Client;

import discovery.DescobridorServico;

public abstract class EnderecoCommand<T> extends ServiceCommand<T> {

	public EnderecoCommand(HystrixCommandGroupKey key, Client client, DescobridorServico descobridorServico) {
		super(key, HystrixThreadPoolKey.Factory.asKey("endereco"), client, descobridorServico);
    }
}
