package gp.server.cobranca.resource;

import com.yammer.metrics.annotation.ExceptionMetered;
import com.yammer.metrics.annotation.Metered;
import com.yammer.metrics.annotation.Timed;
import gp.api.Cobranca;
import gp.monitoring.Tipos;
import gp.server.cobranca.CobrancaService;
import gp.server.cobranca.command.InserirCobrancaCommand;
import gp.server.cobranca.command.ListarCobrancasCommand;
import org.skife.jdbi.v2.DBI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/" + CobrancaService.NOME)
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CobrancaResource {

    private static final Logger logger = LoggerFactory.getLogger(CobrancaResource.class);

    private final DBI dbi;

    public CobrancaResource(DBI dbi) {
        this.dbi = dbi;
    }

    @GET
    @Timed(type = Tipos.TEMPO, group = CobrancaService.NOME)
    @Metered(type = Tipos.CHAMADAS, group = CobrancaService.NOME)
    @ExceptionMetered(type = Tipos.EXCEPTIONS, group = CobrancaService.NOME)
    public List<Cobranca> listarPorGp(@QueryParam("gp") long gp) {
        logger.info("Listando cobrancas da gp [{}]", gp);
        return new ListarCobrancasCommand(dbi, gp).execute();
    }

    @POST
    @Timed(type = Tipos.TEMPO, group = CobrancaService.NOME)
    @Metered(type = Tipos.CHAMADAS, group = CobrancaService.NOME)
    @ExceptionMetered(type = Tipos.EXCEPTIONS, group = CobrancaService.NOME)
    public long inserir(@Valid final Cobranca cobranca) {
        logger.info("Inserindo cobranca [{}]", cobranca);
        return new InserirCobrancaCommand(dbi, cobranca).execute();
    }
}
