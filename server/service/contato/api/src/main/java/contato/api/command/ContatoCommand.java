package contato.api.command;

import server.integracao.ServiceCommand;

import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixThreadPoolKey;
import com.sun.jersey.api.client.Client;

import discovery.DescobridorServico;

public abstract class ContatoCommand<T> extends ServiceCommand<T> {

	public ContatoCommand(HystrixCommandGroupKey key, Client client, DescobridorServico descobridorServico) {
		super(key, HystrixThreadPoolKey.Factory.asKey("contato"), client, descobridorServico);
    }
}
