package gp.server.repository;

import gp.api.Gp;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class GpMapper implements ResultSetMapper<Gp> {
    @Override
    public Gp map(int index, ResultSet r, StatementContext ctx) throws SQLException {
        return new Gp.Builder().id(r.getLong("id")).nome(r.getString("nome")).build();
    }
}
