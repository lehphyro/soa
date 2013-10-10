package contato.server.command;

import org.skife.jdbi.v2.DBI;

import contato.server.repository.ContatoRepository;
import server.integracao.DatabaseCommand;
import api.Contato;

public class InserirContatoCommand extends DatabaseCommand<Long> {

    private final Contato contato;

    public InserirContatoCommand(DBI dbi, Contato contato) {
        super(Grupos.CONTATO, Pools.DATABASE, dbi);
        this.contato = contato;
    }

    @Override
    protected Long run() throws Exception {
        try (ContatoRepository repo = dbi.open(ContatoRepository.class)) {
            return repo.inserir(contato);
        }
    }
}
