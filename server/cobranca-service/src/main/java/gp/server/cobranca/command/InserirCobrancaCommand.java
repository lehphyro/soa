package gp.server.cobranca.command;

import gp.api.Cobranca;
import gp.server.cobranca.repository.CobrancaRepository;
import gp.server.integracao.DatabaseCommand;
import org.skife.jdbi.v2.DBI;

public class InserirCobrancaCommand extends DatabaseCommand<Long> {

    private final Cobranca cobranca;

    public InserirCobrancaCommand(DBI dbi, Cobranca cobranca) {
        super(Grupos.COBRANCA, Pools.DATABASE, dbi);
        this.cobranca = cobranca;
    }

    @Override
    protected Long run() throws Exception {
        try (CobrancaRepository repo = dbi.open(CobrancaRepository.class)) {
            return repo.inserir(cobranca);
        }
    }
}
