package gp.server.endereco.command;

import gp.api.Endereco;
import gp.server.endereco.repository.EnderecoRepository;
import gp.server.integracao.DatabaseCommand;
import org.skife.jdbi.v2.DBI;

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
