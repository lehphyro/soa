package pessoa.server.command;

import org.skife.jdbi.v2.DBI;

import api.Pessoa;
import pessoa.server.repository.PessoaRepository;
import server.integracao.DatabaseCommand;

public class InserirPessoaCommand extends DatabaseCommand<Long> {

    private final Pessoa pessoa;

    public InserirPessoaCommand(DBI dbi, Pessoa pessoa) {
        super(Grupos.PESSOA, Pools.DATABASE, dbi);
        this.pessoa = pessoa;
    }

    @Override
    protected Long run() throws Exception {
        try (PessoaRepository repo = dbi.open(PessoaRepository.class)) {
            return repo.inserir(pessoa);
        }
    }
}
