package contato.api.command;

import java.util.List;

import javax.ws.rs.core.MediaType;

import com.netflix.hystrix.HystrixCommandGroupKey;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.GenericType;

import contato.api.Contato;
import discovery.DescobridorServico;

public class ListarContatosCommand extends ContatoCommand<List<Contato>> {

	private final long pessoa;

	public ListarContatosCommand(Client client, DescobridorServico descobridorServico, long pessoa) {
		super(HystrixCommandGroupKey.Factory.asKey("listar-contato"), client, descobridorServico);
		this.pessoa = pessoa;
	}

	@Override
	protected List<Contato> run() throws Exception {
		return client.resource(descobridorServico.getServico().buildUriSpec()).path("/contato").queryParam("pessoa", Long.toString(pessoa))
				.accept(MediaType.APPLICATION_JSON_TYPE).get(new GenericType<List<Contato>>() {});
	}
}
