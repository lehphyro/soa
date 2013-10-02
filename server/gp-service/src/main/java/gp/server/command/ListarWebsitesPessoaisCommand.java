package gp.server.command;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.GenericType;
import gp.api.WebsitePessoal;
import gp.discovery.DescobridorServico;
import gp.server.integracao.ServiceCommand;

import javax.ws.rs.core.MediaType;
import java.util.List;

public class ListarWebsitesPessoaisCommand extends ServiceCommand<List<WebsitePessoal>> {

    private final long gp;

    public ListarWebsitesPessoaisCommand(Client client, DescobridorServico descobridorServico, long gp) {
        super(Grupos.WEBSITE_PESSOAL, Pools.WEBSITE_PESSOAL, client, descobridorServico);
        this.gp = gp;
    }

    @Override
    protected List<WebsitePessoal> run() throws Exception {
        return client.resource(descobridorServico.getServico().buildUriSpec()).path("/website-pessoal").queryParam("gp", Long.toString(gp)).accept(MediaType.APPLICATION_JSON_TYPE).get(new GenericType<List<WebsitePessoal>>() {});
    }
}
