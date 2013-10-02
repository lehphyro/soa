package gp.server.cobranca.repository;

import gp.api.Cobranca;
import gp.api.internal.Enums;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CobrancaMapper implements ResultSetMapper<Cobranca> {
    @Override
    public Cobranca map(int index, ResultSet r, StatementContext ctx) throws SQLException {
        Cobranca.Builder builder = new Cobranca.Builder().id(r.getLong("id"));
        builder.valor(r.getInt("valor"));
        builder.periodo(r.getFloat("periodo"));
        builder.tipo(Enums.getEnum(r.getInt("tipo"), Cobranca.Tipo.class));
        return builder.build();
    }
}
