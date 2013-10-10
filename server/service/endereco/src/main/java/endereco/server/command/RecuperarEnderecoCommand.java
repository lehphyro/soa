package endereco.server.command;

import org.skife.jdbi.v2.DBI;

import endereco.server.repository.EnderecoRepository;
import api.Endereco;
import server.integracao.DatabaseCommand;

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
