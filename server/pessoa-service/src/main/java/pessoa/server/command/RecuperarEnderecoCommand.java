package pessoa.server.command;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.UniformInterfaceException;
import gp.api.Endereco;
import gp.discovery.DescobridorServico;
import gp.server.integracao.ServiceCommand;

import javax.ws.rs.core.MediaType;

public class RecuperarEnderecoCommand extends ServiceCommand<Endereco> {

    private final long id;

    public RecuperarEnderecoCommand(Client client, DescobridorServico descobridorServico, long id) {
        super(Grupos.ENDERECO, Pools.ENDERECO, client, descobridorServico);
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
