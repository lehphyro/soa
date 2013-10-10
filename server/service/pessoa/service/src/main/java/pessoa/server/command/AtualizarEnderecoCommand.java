package pessoa.server.command;

import org.skife.jdbi.v2.DBI;

import pessoa.server.repository.PessoaRepository;
import server.integracao.DatabaseCommand;

import com.netflix.hystrix.HystrixCommandGroupKey;

public class AtualizarEnderecoCommand extends DatabaseCommand<Void> {

    private final long idPessoa;
    private final long idEndereco;

    public AtualizarEnderecoCommand(DBI dbi, long idPessoa, long idEndereco) {
		super(HystrixCommandGroupKey.Factory.asKey("atualizar-endereco"), dbi);
        this.idPessoa = idPessoa;
        this.idEndereco = idEndereco;
    }

    @Override
    protected Void run() throws Exception {
        try (PessoaRepository repo = dbi.open(PessoaRepository.class)) {
            repo.atualizarEndereco(idPessoa, idEndereco);
        }
        return null;
    }
}
