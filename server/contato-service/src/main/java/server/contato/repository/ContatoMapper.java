package server.contato.repository;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import api.Contato;
import api.internal.Enums;

public class ContatoMapper implements ResultSetMapper<Contato> {
    @Override
    public Contato map(int index, ResultSet r, StatementContext ctx) throws SQLException {
        Contato.Builder builder = new Contato.Builder().id(r.getLong("id"));
        builder.valor(r.getString("valor"));
        builder.tipo(Enums.getEnum(r.getInt("tipo"), Contato.Tipo.class));
        return builder.build();
    }
}
