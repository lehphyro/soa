package contato.api.command;

import javax.ws.rs.core.MediaType;

import com.netflix.hystrix.HystrixCommandGroupKey;
import com.sun.jersey.api.client.Client;

import contato.api.Contato;
import discovery.DescobridorServico;

public class InserirContatoCommand extends ContatoCommand<Long> {

    private final Contato contato;
    private final long pessoa;

    public InserirContatoCommand(Client client, DescobridorServico descobridorServico, Contato contato, long pessoa) {
		super(HystrixCommandGroupKey.Factory.asKey("inserir-contato"), client, descobridorServico);
        this.contato = contato;
        this.pessoa = pessoa;
    }

    @Override
    protected Long run() throws Exception {
    	//@formatter:off
        return client.resource(descobridorServico.getServico().buildUriSpec())
        			.path("/contato")
        			.queryParam("pessoa", Long.toString(pessoa))
        			.type(MediaType.APPLICATION_JSON_TYPE)
        			.post(Long.class, contato);
        //@formatter:on
    }
}
