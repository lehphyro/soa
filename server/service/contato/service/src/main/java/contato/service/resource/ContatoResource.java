package contato.service.resource;

import java.util.List;

import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import monitoring.Tipos;

import org.skife.jdbi.v2.DBI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yammer.metrics.annotation.ExceptionMetered;
import com.yammer.metrics.annotation.Metered;
import com.yammer.metrics.annotation.Timed;

import contato.api.Contato;
import contato.service.ContatoService;
import contato.service.command.InserirContatoCommand;
import contato.service.command.ListarContatoCommand;

@Path("/" + ContatoService.NOME)
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ContatoResource {

    private static final Logger logger = LoggerFactory.getLogger(ContatoResource.class);

    private final DBI dbi;

    public ContatoResource(DBI dbi) {
        this.dbi = dbi;
    }

    @GET
    @Timed(type = Tipos.TEMPO, group = ContatoService.NOME)
    @Metered(type = Tipos.CHAMADAS, group = ContatoService.NOME)
    @ExceptionMetered(type = Tipos.EXCEPTIONS, group = ContatoService.NOME)
	public List<Contato> listarPorGp(@QueryParam("pessoa") long pessoa) {
		logger.info("Listando contatos da pessoa [{}]", pessoa);
		return new ListarContatoCommand(dbi, pessoa).execute();
    }

    @POST
    @Timed(type = Tipos.TEMPO, group = ContatoService.NOME)
    @Metered(type = Tipos.CHAMADAS, group = ContatoService.NOME)
    @ExceptionMetered(type = Tipos.EXCEPTIONS, group = ContatoService.NOME)
	public long inserir(@Valid final Contato contato, @QueryParam("pessoa") long pessoa) {
        logger.info("Inserindo contato [{}]", contato);
		return new InserirContatoCommand(dbi, contato, pessoa).execute();
    }
}
