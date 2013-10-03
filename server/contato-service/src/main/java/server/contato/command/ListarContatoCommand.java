package server.contato.command;

import java.util.List;

import org.skife.jdbi.v2.DBI;

import server.contato.repository.ContatoRepository;
import server.integracao.DatabaseCommand;
import api.Contato;

public class ListarContatoCommand extends DatabaseCommand<List<Contato>> {

    private final long pessoa;

    public ListarContatoCommand(DBI dbi, long pessoa) {
        super(Grupos.CONTATO, Pools.DATABASE, dbi);
        this.pessoa = pessoa;
    }

    @Override
    protected List<Contato> run() throws Exception {
        try (ContatoRepository repo = dbi.open(ContatoRepository.class)) {
            return repo.listarPorPessoa(pessoa);
        }
    }
}
