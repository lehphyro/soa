package contato.service.command;

import org.skife.jdbi.v2.DBI;

import server.integracao.DatabaseCommand;

import com.netflix.hystrix.HystrixCommandGroupKey;

import contato.api.Contato;
import contato.service.repository.ContatoRepository;

public class InserirContatoCommand extends DatabaseCommand<Long> {

    private final Contato contato;
	private final long pessoa;

	public InserirContatoCommand(DBI dbi, Contato contato, long pessoa) {
		super(HystrixCommandGroupKey.Factory.asKey("inserir-contato"), dbi);
        this.contato = contato;
		this.pessoa = pessoa;
    }

    @Override
    protected Long run() throws Exception {
        try (ContatoRepository repo = dbi.open(ContatoRepository.class)) {
			return repo.inserir(contato, pessoa);
        }
    }
}
