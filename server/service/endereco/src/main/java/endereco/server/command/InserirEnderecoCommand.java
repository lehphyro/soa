package endereco.server.command;

import org.skife.jdbi.v2.DBI;

import endereco.server.repository.EnderecoRepository;
import api.Endereco;
import server.integracao.DatabaseCommand;

public class InserirEnderecoCommand extends DatabaseCommand<Long> {

    private final Endereco endereco;

    public InserirEnderecoCommand(DBI dbi, Endereco endereco) {
        super(Grupos.ENDERECO, Pools.DATABASE, dbi);
        this.endereco = endereco;
    }

    @Override
    protected Long run() throws Exception {
        try (EnderecoRepository repo = dbi.open(EnderecoRepository.class)) {
            return repo.inserir(endereco);
        }
    }
}
