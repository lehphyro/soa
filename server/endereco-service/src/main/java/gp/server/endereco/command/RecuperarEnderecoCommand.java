package gp.server.endereco.command;

import gp.api.Endereco;
import gp.server.endereco.repository.EnderecoRepository;
import gp.server.integracao.DatabaseCommand;
import org.skife.jdbi.v2.DBI;

public class RecuperarEnderecoCommand extends DatabaseCommand<Endereco> {

    private final long id;

    public RecuperarEnderecoCommand(DBI dbi, long id) {
        super(Grupos.ENDERECO, Pools.DATABASE, dbi);
        this.id = id;
    }

    @Override
    protected Endereco run() throws Exception {
        try (EnderecoRepository repo = dbi.open(EnderecoRepository.class)) {
            return repo.recuperar(id);
        }
    }
}
