package endereco.api.command;

import javax.ws.rs.core.MediaType;

import com.netflix.hystrix.HystrixCommandGroupKey;
import com.sun.jersey.api.client.Client;

import discovery.DescobridorServico;
import endereco.api.Endereco;

public class InserirEnderecoCommand extends EnderecoCommand<Long> {

    private final Endereco endereco;

    public InserirEnderecoCommand(Client client, DescobridorServico descobridorServico, Endereco endereco) {
		super(HystrixCommandGroupKey.Factory.asKey("inserir-endereco"), client, descobridorServico);
        this.endereco = endereco;
    }

    @Override
    protected Long run() throws Exception {
        return client.resource(descobridorServico.getServico().buildUriSpec()).path("/endereco").type(MediaType.APPLICATION_JSON_TYPE).post(Long.class, endereco);
    }
}
