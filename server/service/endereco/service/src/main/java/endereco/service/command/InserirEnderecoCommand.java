package endereco.service.command;

import org.skife.jdbi.v2.DBI;

import server.integracao.DatabaseCommand;

import com.netflix.hystrix.HystrixCommandGroupKey;

import endereco.api.Endereco;
import endereco.service.repository.EnderecoRepository;

public class InserirEnderecoCommand extends DatabaseCommand<Long> {

    private final Endereco endereco;

    public InserirEnderecoCommand(DBI dbi, Endereco endereco) {
		super(HystrixCommandGroupKey.Factory.asKey("inserir-endereco"), dbi);
        this.endereco = endereco;
    }

    @Override
    protected Long run() throws Exception {
        try (EnderecoRepository repo = dbi.open(EnderecoRepository.class)) {
            return repo.inserir(endereco);
        }
    }
}
