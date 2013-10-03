package pessoa.server.command;

import gp.server.integracao.DatabaseCommand;

import org.skife.jdbi.v2.DBI;

import pessoa.server.repository.PessoaRepository;

public class AtualizarEnderecoCommand extends DatabaseCommand<Void> {

    private final long idPessoa;
    private final long idEndereco;

    public AtualizarEnderecoCommand(DBI dbi, long idPessoa, long idEndereco) {
        super(Grupos.ENDERECO, Pools.DATABASE, dbi);
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