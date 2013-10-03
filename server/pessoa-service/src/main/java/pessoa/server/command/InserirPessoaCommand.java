package pessoa.server.command;

import gp.api.Pessoa;
import gp.server.integracao.DatabaseCommand;

import org.skife.jdbi.v2.DBI;

import pessoa.server.repository.PessoaRepository;

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
