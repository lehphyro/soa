package gp.server.command;

import gp.server.integracao.DatabaseCommand;
import gp.server.repository.GpRepository;
import org.skife.jdbi.v2.DBI;

public class AtualizarEnderecoGpCommand extends DatabaseCommand<Void> {

    private final long idGp;
    private final long idEndereco;

    public AtualizarEnderecoGpCommand(DBI dbi, long idGp, long idEndereco) {
        super(Grupos.ENDERECO, Pools.DATABASE, dbi);
        this.idGp = idGp;
        this.idEndereco = idEndereco;
    }

    @Override
    protected Void run() throws Exception {
        try (GpRepository repo = dbi.open(GpRepository.class)) {
            repo.atualizarEndereco(idGp, idEndereco);
        }
        return null;
    }
}
