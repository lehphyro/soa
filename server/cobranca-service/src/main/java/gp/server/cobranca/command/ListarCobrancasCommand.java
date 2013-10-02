package gp.server.cobranca.command;

import gp.api.Cobranca;
import gp.server.cobranca.repository.CobrancaRepository;
import gp.server.integracao.DatabaseCommand;
import org.skife.jdbi.v2.DBI;

import java.util.List;

public class ListarCobrancasCommand extends DatabaseCommand<List<Cobranca>> {

    private final long gp;

    public ListarCobrancasCommand(DBI dbi, long gp) {
        super(Grupos.COBRANCA, Pools.DATABASE, dbi);
        this.gp = gp;
    }

    @Override
    protected List<Cobranca> run() throws Exception {
        try (CobrancaRepository repo = dbi.open(CobrancaRepository.class)) {
            return repo.listarPorGp(gp);
        }
    }
}
