package endereco.server.repository;

import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import api.Endereco;

import java.sql.ResultSet;
import java.sql.SQLException;

public class EnderecoMapper implements ResultSetMapper<Endereco> {
    @Override
    public Endereco map(int index, ResultSet r, StatementContext ctx) throws SQLException {
        Endereco.Builder builder = new Endereco.Builder().id(r.getLong("id"));
        builder.logradouro(r.getString("logradouro"));
        builder.numero(r.getString("numero"));
        builder.complemento(r.getString("complemento"));
        builder.bairro(r.getString("bairro"));
        return builder.build();
    }
}
