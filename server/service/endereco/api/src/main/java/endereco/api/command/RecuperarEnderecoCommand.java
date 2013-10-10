package endereco.api.command;

import javax.ws.rs.core.MediaType;

import com.netflix.hystrix.HystrixCommandGroupKey;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.UniformInterfaceException;

import discovery.DescobridorServico;
import endereco.api.Endereco;

public class RecuperarEnderecoCommand extends EnderecoCommand<Endereco> {

    private final long id;

    public RecuperarEnderecoCommand(Client client, DescobridorServico descobridorServico, long id) {
		super(HystrixCommandGroupKey.Factory.asKey("recuperar-endereco"), client, descobridorServico);
        this.id = id;
    }

    @Override
    protected Endereco run() throws Exception {
        try {
            return client.resource(descobridorServico.getServico().buildUriSpec()).path("/endereco").path(Long.toString(id)).accept(MediaType.APPLICATION_JSON_TYPE).get(Endereco.class);
        } catch (UniformInterfaceException e) {
            if (e.getResponse().getClientResponseStatus() == ClientResponse.Status.NO_CONTENT) {
                return null;
            } else {
                throw e;
            }
        }
    }
}
