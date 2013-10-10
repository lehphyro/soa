package pessoa.server.command;

import org.skife.jdbi.v2.DBI;

import pessoa.api.Pessoa;
import pessoa.server.repository.PessoaRepository;
import server.integracao.DatabaseCommand;

import com.netflix.hystrix.HystrixCommandGroupKey;

public class RecuperarPessoaCommand extends DatabaseCommand<Pessoa> {

    private final long id;

    public RecuperarPessoaCommand(DBI dbi, long id) {
		super(HystrixCommandGroupKey.Factory.asKey("recuperar-pessoa"), dbi);
        this.id = id;
    }

    @Override
    protected Pessoa run() throws Exception {
        try (PessoaRepository repo = dbi.open(PessoaRepository.class)) {
            return repo.recuperar(id);
        }
    }
}
