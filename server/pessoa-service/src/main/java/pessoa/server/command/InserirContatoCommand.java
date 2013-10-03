package pessoa.server.command;

import com.sun.jersey.api.client.Client;
import gp.api.Contato;
import gp.discovery.DescobridorServico;
import gp.server.integracao.ServiceCommand;

import javax.ws.rs.core.MediaType;

public class InserirContatoCommand extends ServiceCommand<Long> {

    private final Contato contato;

    public InserirContatoCommand(Client client, DescobridorServico descobridorServico, Contato contato) {
        super(Grupos.CONTATO, Pools.CONTATO, client, descobridorServico);
        this.contato = contato;
    }

    @Override
    protected Long run() throws Exception {
        return client.resource(descobridorServico.getServico().buildUriSpec()).path("/contato").type(MediaType.APPLICATION_JSON_TYPE).post(Long.class, contato);
    }
}
