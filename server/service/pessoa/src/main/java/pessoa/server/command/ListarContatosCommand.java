package pessoa.server.command;

import java.util.List;

import javax.ws.rs.core.MediaType;

import server.integracao.ServiceCommand;
import api.Contato;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.GenericType;

import discovery.DescobridorServico;

public class ListarContatosCommand extends ServiceCommand<List<Contato>> {

	private final long pessoa;

	public ListarContatosCommand(Client client, DescobridorServico descobridorServico, long pessoa) {
		super(Grupos.CONTATO, Pools.CONTATO, client, descobridorServico);
		this.pessoa = pessoa;
	}

	@Override
	protected List<Contato> run() throws Exception {
		return client.resource(descobridorServico.getServico().buildUriSpec()).path("/contato").queryParam("pessoa", Long.toString(pessoa))
				.accept(MediaType.APPLICATION_JSON_TYPE).get(new GenericType<List<Contato>>() {
				});
	}
}
