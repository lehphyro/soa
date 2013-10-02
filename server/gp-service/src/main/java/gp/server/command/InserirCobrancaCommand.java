package gp.server.command;

import com.sun.jersey.api.client.Client;
import gp.api.Cobranca;
import gp.discovery.DescobridorServico;
import gp.server.integracao.ServiceCommand;

import javax.ws.rs.core.MediaType;

public class InserirCobrancaCommand extends ServiceCommand<Long> {

    private final Cobranca cobranca;

    public InserirCobrancaCommand(Client client, DescobridorServico descobridorServico, Cobranca cobranca) {
        super(Grupos.COBRANCA, Pools.COBRANCA, client, descobridorServico);
        this.cobranca = cobranca;
    }

    @Override
    protected Long run() throws Exception {
        return client.resource(descobridorServico.getServico().buildUriSpec()).path("/cobranca").type(MediaType.APPLICATION_JSON_TYPE).post(Long.class, cobranca);
    }
}
