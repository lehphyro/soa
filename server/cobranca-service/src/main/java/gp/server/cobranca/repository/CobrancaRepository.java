package gp.server.cobranca.repository;

import gp.api.Cobranca;
import gp.database.binding.BindDeepBean;
import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.GetGeneratedKeys;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.Mapper;
import org.skife.jdbi.v2.sqlobject.mixins.Transactional;

import java.util.List;

public interface CobrancaRepository extends Transactional<CobrancaRepository>, AutoCloseable {

    @SqlQuery("SELECT id, tipo, valor, periodo FROM cobranca WHERE gp = :gp")
    @Mapper(CobrancaMapper.class)
    List<Cobranca> listarPorGp(@Bind("gp") long gp);

    @SqlUpdate("INSERT INTO cobranca (gp, tipo, valor, periodo) VALUES (:gp.id, :tipo, :valor, :periodo)")
    @GetGeneratedKeys
    long inserir(@BindDeepBean Cobranca cobranca);
}
