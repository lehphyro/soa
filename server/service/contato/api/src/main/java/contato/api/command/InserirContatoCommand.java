package contato.api.command;

import javax.ws.rs.core.MediaType;

import com.netflix.hystrix.HystrixCommandGroupKey;
import com.sun.jersey.api.client.Client;

import contato.api.Contato;
import discovery.DescobridorServico;

public class InserirContatoCommand extends ContatoCommand<Long> {

    private final Contato contato;

    public InserirContatoCommand(Client client, DescobridorServico descobridorServico, Contato contato) {
		super(HystrixCommandGroupKey.Factory.asKey("inserir-contato"), client, descobridorServico);
        this.contato = contato;
    }

    @Override
    protected Long run() throws Exception {
        return client.resource(descobridorServico.getServico().buildUriSpec()).path("/contato").type(MediaType.APPLICATION_JSON_TYPE).post(Long.class, contato);
    }
}
