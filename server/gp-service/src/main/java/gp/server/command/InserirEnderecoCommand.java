package gp.server.command;

import com.sun.jersey.api.client.Client;
import gp.api.Endereco;
import gp.discovery.DescobridorServico;
import gp.server.integracao.ServiceCommand;

import javax.ws.rs.core.MediaType;

public class InserirEnderecoCommand extends ServiceCommand<Long> {

    private final Endereco endereco;

    public InserirEnderecoCommand(Client client, DescobridorServico descobridorServico, Endereco endereco) {
        super(Grupos.ENDERECO, Pools.ENDERECO, client, descobridorServico);
        this.endereco = endereco;
    }

    @Override
    protected Long run() throws Exception {
        return client.resource(descobridorServico.getServico().buildUriSpec()).path("/endereco").type(MediaType.APPLICATION_JSON_TYPE).post(Long.class, endereco);
    }
}
