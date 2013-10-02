package gp.server.resource;

import gp.api.Cobranca;
import gp.api.Contato;
import gp.api.Endereco;
import gp.api.Gp;
import gp.discovery.DescobridorServico;
import gp.monitoring.Tipos;
import gp.server.GpService;
import gp.server.command.AtualizarEnderecoGpCommand;
import gp.server.command.InserirCobrancaCommand;
import gp.server.command.InserirContatoCommand;
import gp.server.command.InserirEnderecoCommand;
import gp.server.command.InserirGpCommand;
import gp.server.command.ListarCobrancasCommand;
import gp.server.command.ListarContatosCommand;
import gp.server.command.RecuperarEnderecoCommand;
import gp.server.command.RecuperarGpCommand;
import gp.server.repository.GpRepository;

import java.util.List;
import java.util.concurrent.Future;

import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.skife.jdbi.v2.DBI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sun.jersey.api.client.Client;
import com.yammer.metrics.annotation.ExceptionMetered;
import com.yammer.metrics.annotation.Metered;
import com.yammer.metrics.annotation.Timed;

@Path("/" + GpService.NOME)
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class GpResource {

    private static final Logger logger = LoggerFactory.getLogger(GpResource.class);

    private final DBI dbi;

    private final Client client;

    private final DescobridorServico descobridorEndereco;

    private final DescobridorServico descobridorContato;

    private final DescobridorServico descobridorCobranca;

	public GpResource(DBI dbi, Client client, DescobridorServico descobridorEndereco, DescobridorServico descobridorContato,
			DescobridorServico descobridorCobranca) {
        this.dbi = dbi;
        this.client = client;
        this.descobridorEndereco = descobridorEndereco;
        this.descobridorContato = descobridorContato;
        this.descobridorCobranca = descobridorCobranca;
    }

    @GET
    @Timed(type = Tipos.TEMPO, group = GpService.NOME)
    @Metered(type = Tipos.CHAMADAS, group = GpService.NOME)
    @ExceptionMetered(type = Tipos.EXCEPTIONS, group = GpService.NOME)
    public List<Gp> getAll() throws Exception {
        logger.info("Obtendo todas as gps");
        try (GpRepository repo = dbi.open(GpRepository.class)) {
            return repo.findAll();
        }
    }

    @Path("/{id}")
    @GET
    @Timed(type = Tipos.TEMPO, group = GpService.NOME)
    @Metered(type = Tipos.CHAMADAS, group = GpService.NOME)
    @ExceptionMetered(type = Tipos.EXCEPTIONS, group = GpService.NOME)
    public Gp recuperar(@PathParam("id") long id) throws Exception {
        logger.info("Recuperando gp com id [{}]", id);

        Gp gp = new RecuperarGpCommand(dbi, id).execute();
        Future<Endereco> futureEndereco = new RecuperarEnderecoCommand(client, descobridorEndereco, id).queue();
        Future<List<Cobranca>> futureCobrancas = new ListarCobrancasCommand(client, descobridorCobranca, id).queue();
        Future<List<Contato>> futureContatos = new ListarContatosCommand(client, descobridorContato, id).queue();

        Gp.Builder builder = new Gp.Builder(gp);
        builder.endereco(futureEndereco.get());
        builder.cobrancas(futureCobrancas.get());
        builder.contatos(futureContatos.get());

        return builder.build();
    }

    @POST
    @Timed(type = Tipos.TEMPO, group = GpService.NOME)
    @Metered(type = Tipos.CHAMADAS, group = GpService.NOME)
    @ExceptionMetered(type = Tipos.EXCEPTIONS, group = GpService.NOME)
    public long inserirGp(@Valid Gp gp) {
        logger.info("Inserindo gp com nome [{}]", gp.getNome());
        return new InserirGpCommand(dbi, gp).execute();
    }

    @Path("/{id}/endereco")
    @POST
    @Timed(type = Tipos.TEMPO, group = GpService.NOME)
    @Metered(type = Tipos.CHAMADAS, group = GpService.NOME)
    @ExceptionMetered(type = Tipos.EXCEPTIONS, group = GpService.NOME)
    public long inserirEndereco(@PathParam("id") final long id, @Valid final Endereco endereco) {
        logger.info("Inserindo endereco [{}] para gp [{}]", endereco, id);
        long idEndereco = new InserirEnderecoCommand(client, descobridorEndereco, endereco).execute();
        new AtualizarEnderecoGpCommand(dbi, id, idEndereco).execute();
        return idEndereco;
    }

    @Path("/{id}/contato")
    @POST
    @Timed(type = Tipos.TEMPO, group = GpService.NOME)
    @Metered(type = Tipos.CHAMADAS, group = GpService.NOME)
    @ExceptionMetered(type = Tipos.EXCEPTIONS, group = GpService.NOME)
    public long inserirContato(@PathParam("id") final long id, @Valid final Contato contato) {
        logger.info("Inserindo contato [{}] para gp [{}]", contato, id);
        Contato contatoComGp = new Contato.Builder(contato).gp(new Gp.Builder().id(id).build()).build();
        return new InserirContatoCommand(client, descobridorContato, contatoComGp).execute();
    }

    @Path("/{id}/cobranca")
    @POST
    @Timed(type = Tipos.TEMPO, group = GpService.NOME)
    @Metered(type = Tipos.CHAMADAS, group = GpService.NOME)
    @ExceptionMetered(type = Tipos.EXCEPTIONS, group = GpService.NOME)
    public long inserirCobranca(@PathParam("id") long id, @Valid Cobranca cobranca) {
        logger.info("Inserindo cobranca [{}] para gp [{}]", cobranca, id);
        Cobranca cobrancaComGp = new Cobranca.Builder(cobranca).gp(new Gp.Builder().id(id).build()).build();
        return new InserirCobrancaCommand(client, descobridorCobranca, cobrancaComGp).execute();
    }
}
