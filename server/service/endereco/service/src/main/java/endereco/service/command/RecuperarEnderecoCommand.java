package endereco.service.command;

import org.skife.jdbi.v2.DBI;

import server.integracao.DatabaseCommand;

import com.netflix.hystrix.HystrixCommandGroupKey;

import endereco.api.Endereco;
import endereco.service.repository.EnderecoRepository;

public class RecuperarEnderecoCommand extends DatabaseCommand<Endereco> {

    private final long id;

    public RecuperarEnderecoCommand(DBI dbi, long id) {
		super(HystrixCommandGroupKey.Factory.asKey("recuperar-endereco"), dbi);
        this.id = id;
    }

    @Override
    protected Endereco run() throws Exception {
        try (EnderecoRepository repo = dbi.open(EnderecoRepository.class)) {
            return repo.recuperar(id);
        }
    }
}
