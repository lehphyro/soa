package server.endereco.command;

import org.skife.jdbi.v2.DBI;

import api.Endereco;
import server.endereco.repository.EnderecoRepository;
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
