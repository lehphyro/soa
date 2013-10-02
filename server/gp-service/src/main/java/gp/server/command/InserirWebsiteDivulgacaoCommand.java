package gp.server.command;

import com.sun.jersey.api.client.Client;
import gp.api.WebsiteDivulgacao;
import gp.discovery.DescobridorServico;
import gp.server.integracao.ServiceCommand;

import javax.ws.rs.core.MediaType;

public class InserirWebsiteDivulgacaoCommand extends ServiceCommand<Long> {

    private final WebsiteDivulgacao websiteDivulgacao;

    public InserirWebsiteDivulgacaoCommand(Client client, DescobridorServico descobridorServico, WebsiteDivulgacao websiteDivulgacao) {
        super(Grupos.WEBSITE_DIVULGACAO, Pools.WEBSITE_DIVULGACAO, client, descobridorServico);
        this.websiteDivulgacao = websiteDivulgacao;
    }

    @Override
    protected Long run() throws Exception {
        return client.resource(descobridorServico.getServico().buildUriSpec()).path("/website-divulgacao").type(MediaType.APPLICATION_JSON_TYPE).post(Long.class, websiteDivulgacao);
    }
}
