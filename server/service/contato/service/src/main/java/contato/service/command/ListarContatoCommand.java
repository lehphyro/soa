package contato.service.command;

import java.util.List;

import org.skife.jdbi.v2.DBI;

import server.integracao.DatabaseCommand;

import com.netflix.hystrix.HystrixCommandGroupKey;

import contato.api.Contato;
import contato.service.repository.ContatoRepository;

public class ListarContatoCommand extends DatabaseCommand<List<Contato>> {

    private final long pessoa;

    public ListarContatoCommand(DBI dbi, long pessoa) {
		super(HystrixCommandGroupKey.Factory.asKey("listar-contato"), dbi);
        this.pessoa = pessoa;
    }

    @Override
    protected List<Contato> run() throws Exception {
        try (ContatoRepository repo = dbi.open(ContatoRepository.class)) {
            return repo.listarPorPessoa(pessoa);
        }
    }
}
