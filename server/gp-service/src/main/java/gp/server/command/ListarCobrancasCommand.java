package gp.server.command;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.GenericType;
import gp.api.Cobranca;
import gp.discovery.DescobridorServico;
import gp.server.integracao.ServiceCommand;

import javax.ws.rs.core.MediaType;
import java.util.List;

public class ListarCobrancasCommand extends ServiceCommand<List<Cobranca>> {

    private final long gp;

    public ListarCobrancasCommand(Client client, DescobridorServico descobridorServico, long gp) {
        super(Grupos.COBRANCA, Pools.COBRANCA, client, descobridorServico);
        this.gp = gp;
    }

    @Override
    protected List<Cobranca> run() throws Exception {
        return client.resource(descobridorServico.getServico().buildUriSpec()).path("/cobranca").queryParam("gp", Long.toString(gp)).accept(MediaType.APPLICATION_JSON_TYPE).get(new GenericType<List<Cobranca>>() {});
    }
}
