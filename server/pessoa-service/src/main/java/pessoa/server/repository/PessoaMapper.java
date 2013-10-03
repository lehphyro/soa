package pessoa.server.repository;

import gp.api.Pessoa;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PessoaMapper implements ResultSetMapper<Pessoa> {
    @Override
    public Pessoa map(int index, ResultSet r, StatementContext ctx) throws SQLException {
        return new Pessoa.Builder().id(r.getLong("id")).nome(r.getString("nome")).build();
    }
}
