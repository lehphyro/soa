package gp.server.command;

import gp.api.Gp;
import gp.server.integracao.DatabaseCommand;
import gp.server.repository.GpRepository;
import org.skife.jdbi.v2.DBI;

public class InserirGpCommand extends DatabaseCommand<Long> {

    private final Gp gp;

    public InserirGpCommand(DBI dbi, Gp gp) {
        super(Grupos.GP, Pools.DATABASE, dbi);
        this.gp = gp;
    }

    @Override
    protected Long run() throws Exception {
        try (GpRepository repo = dbi.open(GpRepository.class)) {
            return repo.inserir(gp);
        }
    }
}
