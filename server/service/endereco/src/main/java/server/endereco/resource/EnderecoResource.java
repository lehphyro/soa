package server.endereco.resource;

import api.Endereco;

import com.yammer.metrics.annotation.ExceptionMetered;
import com.yammer.metrics.annotation.Metered;
import com.yammer.metrics.annotation.Timed;

import monitoring.Tipos;

import org.skife.jdbi.v2.DBI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import server.endereco.EnderecoService;
import server.endereco.command.InserirEnderecoCommand;
import server.endereco.command.RecuperarEnderecoCommand;

import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/endereco")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class EnderecoResource {

    private static final Logger logger = LoggerFactory.getLogger(EnderecoResource.class);

    private final DBI dbi;

    public EnderecoResource(DBI dbi) {
        this.dbi = dbi;
    }

    @Path("/{id}")
    @GET
    @Timed(type = Tipos.TEMPO, group = EnderecoService.NOME)
    @Metered(type = Tipos.CHAMADAS, group = EnderecoService.NOME)
    @ExceptionMetered(type = Tipos.EXCEPTIONS, group = EnderecoService.NOME)
    public Endereco recuperar(@PathParam("id") long id) {
        logger.info("Recuperando endereco com id [{}]", id);
        return new RecuperarEnderecoCommand(dbi, id).execute();
    }

    @POST
    @Timed(type = Tipos.TEMPO, group = EnderecoService.NOME)
    @Metered(type = Tipos.CHAMADAS, group = EnderecoService.NOME)
    @ExceptionMetered(type = Tipos.EXCEPTIONS, group = EnderecoService.NOME)
    public long inserir(@Valid final Endereco endereco) {
        logger.info("Inserindo endereco [{}]", endereco);
        return new InserirEnderecoCommand(dbi, endereco).execute();
    }
}
