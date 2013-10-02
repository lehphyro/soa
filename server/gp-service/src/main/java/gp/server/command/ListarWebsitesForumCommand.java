package gp.server.command;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.GenericType;
import gp.api.WebsiteForum;
import gp.discovery.DescobridorServico;
import gp.server.integracao.ServiceCommand;

import javax.ws.rs.core.MediaType;
import java.util.List;

public class ListarWebsitesForumCommand extends ServiceCommand<List<WebsiteForum>> {

    private final long gp;

    public ListarWebsitesForumCommand(Client client, DescobridorServico descobridorServico, long gp) {
        super(Grupos.WEBSITE_FORUM, Pools.WEBSITE_FORUM, client, descobridorServico);
        this.gp = gp;
    }

    @Override
    protected List<WebsiteForum> run() throws Exception {
        return client.resource(descobridorServico.getServico().buildUriSpec()).path("/website-forum").queryParam("gp", Long.toString(gp)).accept(MediaType.APPLICATION_JSON_TYPE).get(new GenericType<List<WebsiteForum>>() {});
    }
}
