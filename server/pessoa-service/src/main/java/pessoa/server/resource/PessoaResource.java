package pessoa.server.resource;

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

import monitoring.Tipos;

import org.skife.jdbi.v2.DBI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pessoa.server.PessoaService;
import pessoa.server.command.AtualizarEnderecoCommand;
import pessoa.server.command.InserirContatoCommand;
import pessoa.server.command.InserirEnderecoCommand;
import pessoa.server.command.InserirPessoaCommand;
import pessoa.server.command.ListarContatosCommand;
import pessoa.server.command.RecuperarEnderecoCommand;
import pessoa.server.command.RecuperarPessoaCommand;
import pessoa.server.repository.PessoaRepository;
import api.Contato;
import api.Endereco;
import api.Pessoa;

import com.sun.jersey.api.client.Client;
import com.yammer.metrics.annotation.ExceptionMetered;
import com.yammer.metrics.annotation.Metered;
import com.yammer.metrics.annotation.Timed;

import discovery.DescobridorServico;

@Path("/" + PessoaService.NOME)
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PessoaResource {

    private static final Logger logger = LoggerFactory.getLogger(PessoaResource.class);

    private final DBI dbi;

    private final Client client;

    private final DescobridorServico descobridorEndereco;

    private final DescobridorServico descobridorContato;

	public PessoaResource(DBI dbi, Client client, DescobridorServico descobridorEndereco, DescobridorServico descobridorContato) {
        this.dbi = dbi;
        this.client = client;
        this.descobridorEndereco = descobridorEndereco;
        this.descobridorContato = descobridorContato;
    }

    @GET
    @Timed(type = Tipos.TEMPO, group = PessoaService.NOME)
    @Metered(type = Tipos.CHAMADAS, group = PessoaService.NOME)
    @ExceptionMetered(type = Tipos.EXCEPTIONS, group = PessoaService.NOME)
    public List<Pessoa> getAll() throws Exception {
		logger.info("Obtendo todas as pessoas");
        try (PessoaRepository repo = dbi.open(PessoaRepository.class)) {
            return repo.findAll();
        }
    }

    @Path("/{id}")
    @GET
    @Timed(type = Tipos.TEMPO, group = PessoaService.NOME)
    @Metered(type = Tipos.CHAMADAS, group = PessoaService.NOME)
    @ExceptionMetered(type = Tipos.EXCEPTIONS, group = PessoaService.NOME)
    public Pessoa recuperar(@PathParam("id") long id) throws Exception {
		logger.info("Recuperando pessoa com id [{}]", id);

        Pessoa gp = new RecuperarPessoaCommand(dbi, id).execute();
        Future<Endereco> futureEndereco = new RecuperarEnderecoCommand(client, descobridorEndereco, id).queue();
        Future<List<Contato>> futureContatos = new ListarContatosCommand(client, descobridorContato, id).queue();

        Pessoa.Builder builder = new Pessoa.Builder(gp);
        builder.endereco(futureEndereco.get());
        builder.contatos(futureContatos.get());

        return builder.build();
    }

    @POST
    @Timed(type = Tipos.TEMPO, group = PessoaService.NOME)
    @Metered(type = Tipos.CHAMADAS, group = PessoaService.NOME)
    @ExceptionMetered(type = Tipos.EXCEPTIONS, group = PessoaService.NOME)
    public long inserirGp(@Valid Pessoa gp) {
		logger.info("Inserindo pessoa com nome [{}]", gp.getNome());
        return new InserirPessoaCommand(dbi, gp).execute();
    }

    @Path("/{id}/endereco")
    @POST
    @Timed(type = Tipos.TEMPO, group = PessoaService.NOME)
    @Metered(type = Tipos.CHAMADAS, group = PessoaService.NOME)
    @ExceptionMetered(type = Tipos.EXCEPTIONS, group = PessoaService.NOME)
    public long inserirEndereco(@PathParam("id") final long id, @Valid final Endereco endereco) {
		logger.info("Inserindo endereco [{}] para pessoa [{}]", endereco, id);
        long idEndereco = new InserirEnderecoCommand(client, descobridorEndereco, endereco).execute();
        new AtualizarEnderecoCommand(dbi, id, idEndereco).execute();
        return idEndereco;
    }

    @Path("/{id}/contato")
    @POST
    @Timed(type = Tipos.TEMPO, group = PessoaService.NOME)
    @Metered(type = Tipos.CHAMADAS, group = PessoaService.NOME)
    @ExceptionMetered(type = Tipos.EXCEPTIONS, group = PessoaService.NOME)
    public long inserirContato(@PathParam("id") final long id, @Valid final Contato contato) {
		logger.info("Inserindo contato [{}] para pessoa [{}]", contato, id);
        Contato contatoComGp = new Contato.Builder(contato).pessoa(new Pessoa.Builder().id(id).build()).build();
        return new InserirContatoCommand(client, descobridorContato, contatoComGp).execute();
    }
}
