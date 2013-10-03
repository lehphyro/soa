package pessoa.server.command;

import api.Contato;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.GenericType;

import discovery.DescobridorServico;

import javax.ws.rs.core.MediaType;

import server.integracao.ServiceCommand;

import java.util.List;

public class ListarContatosCommand extends ServiceCommand<List<Contato>> {

    private final long gp;

    public ListarContatosCommand(Client client, DescobridorServico descobridorServico, long gp) {
        super(Grupos.CONTATO, Pools.CONTATO, client, descobridorServico);
        this.gp = gp;
    }

    @Override
    protected List<Contato> run() throws Exception {
        return client.resource(descobridorServico.getServico().buildUriSpec()).path("/contato").queryParam("gp", Long.toString(gp)).accept(MediaType.APPLICATION_JSON_TYPE).get(new GenericType<List<Contato>>() {});
    }
}
