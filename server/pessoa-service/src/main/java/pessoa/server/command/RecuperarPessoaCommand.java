package pessoa.server.command;

import gp.api.Pessoa;
import gp.server.integracao.DatabaseCommand;

import org.skife.jdbi.v2.DBI;

import pessoa.server.repository.PessoaRepository;

public class RecuperarPessoaCommand extends DatabaseCommand<Pessoa> {

    private final long id;

    public RecuperarPessoaCommand(DBI dbi, long id) {
        super(Grupos.PESSOA, Pools.DATABASE, dbi);
        this.id = id;
    }

    @Override
    protected Pessoa run() throws Exception {
        try (PessoaRepository repo = dbi.open(PessoaRepository.class)) {
            return repo.recuperar(id);
        }
    }
}
