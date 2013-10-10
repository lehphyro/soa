package pessoa.service.command;

import org.skife.jdbi.v2.DBI;

import pessoa.api.Pessoa;
import pessoa.service.repository.PessoaRepository;
import server.integracao.DatabaseCommand;

import com.netflix.hystrix.HystrixCommandGroupKey;

public class InserirPessoaCommand extends DatabaseCommand<Long> {

    private final Pessoa pessoa;

    public InserirPessoaCommand(DBI dbi, Pessoa pessoa) {
		super(HystrixCommandGroupKey.Factory.asKey("inserir-pessoa"), dbi);
        this.pessoa = pessoa;
    }

    @Override
    protected Long run() throws Exception {
        try (PessoaRepository repo = dbi.open(PessoaRepository.class)) {
            return repo.inserir(pessoa);
        }
    }
}
