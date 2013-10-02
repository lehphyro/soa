package gp.server.command;

import com.sun.jersey.api.client.Client;
import gp.api.WebsiteForum;
import gp.discovery.DescobridorServico;
import gp.server.integracao.ServiceCommand;

import javax.ws.rs.core.MediaType;

public class InserirWebsiteForumCommand extends ServiceCommand<Long> {

    private final WebsiteForum websiteForum;

    public InserirWebsiteForumCommand(Client client, DescobridorServico descobridorServico, WebsiteForum websiteForum) {
        super(Grupos.WEBSITE_FORUM, Pools.WEBSITE_FORUM, client, descobridorServico);
        this.websiteForum = websiteForum;
    }

    @Override
    protected Long run() throws Exception {
        return client.resource(descobridorServico.getServico().buildUriSpec()).path("/website-forum").type(MediaType.APPLICATION_JSON_TYPE).post(Long.class, websiteForum);
    }
}
