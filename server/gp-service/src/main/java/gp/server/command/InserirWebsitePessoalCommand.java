package gp.server.command;

import com.sun.jersey.api.client.Client;
import gp.api.WebsitePessoal;
import gp.discovery.DescobridorServico;
import gp.server.integracao.ServiceCommand;

import javax.ws.rs.core.MediaType;

public class InserirWebsitePessoalCommand extends ServiceCommand<Long> {

    private final WebsitePessoal websitePessoal;

    public InserirWebsitePessoalCommand(Client client, DescobridorServico descobridorServico, WebsitePessoal websitePessoal) {
        super(Grupos.WEBSITE_PESSOAL, Pools.WEBSITE_PESSOAL, client, descobridorServico);
        this.websitePessoal = websitePessoal;
    }

    @Override
    protected Long run() throws Exception {
        return client.resource(descobridorServico.getServico().buildUriSpec()).path("/website-pessoal").type(MediaType.APPLICATION_JSON_TYPE).post(Long.class, websitePessoal);
    }
}
