package gp.server.command;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.GenericType;
import gp.api.WebsiteDivulgacao;
import gp.discovery.DescobridorServico;
import gp.server.integracao.ServiceCommand;

import javax.ws.rs.core.MediaType;
import java.util.List;

public class ListarWebsitesDivulgacaoCommand extends ServiceCommand<List<WebsiteDivulgacao>> {

    private final long gp;

    public ListarWebsitesDivulgacaoCommand(Client client, DescobridorServico descobridorServico, long gp) {
        super(Grupos.WEBSITE_DIVULGACAO, Pools.WEBSITE_DIVULGACAO, client, descobridorServico);
        this.gp = gp;
    }

    @Override
    protected List<WebsiteDivulgacao> run() throws Exception {
        return client.resource(descobridorServico.getServico().buildUriSpec()).path("/website-divulgacao").queryParam("gp", Long.toString(gp)).accept(MediaType.APPLICATION_JSON_TYPE).get(new GenericType<List<WebsiteDivulgacao>>() {});
    }
}
