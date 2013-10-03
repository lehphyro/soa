package gp.server.command;

import gp.api.Pessoa;
import gp.server.integracao.DatabaseCommand;
import gp.server.repository.GpRepository;
import org.skife.jdbi.v2.DBI;

public class RecuperarGpCommand extends DatabaseCommand<Pessoa> {

    private final long id;

    public RecuperarGpCommand(DBI dbi, long id) {
        super(Grupos.GP, Pools.DATABASE, dbi);
        this.id = id;
    }

    @Override
    protected Pessoa run() throws Exception {
        try (GpRepository repo = dbi.open(GpRepository.class)) {
            return repo.recuperar(id);
        }
    }
}
