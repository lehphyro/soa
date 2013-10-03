package gp.server.repository;

import gp.api.Pessoa;
import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.BindBean;
import org.skife.jdbi.v2.sqlobject.GetGeneratedKeys;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;
import org.skife.jdbi.v2.sqlobject.mixins.Transactional;

import java.util.List;

@RegisterMapper(GpMapper.class)
public interface GpRepository extends Transactional<GpRepository>, AutoCloseable {
    @SqlQuery("SELECT id, nome FROM gp")
    List<Pessoa> findAll();

    @SqlQuery("SELECT id, nome FROM gp WHERE id = :id")
    Pessoa recuperar(@Bind("id") long id);

    @SqlUpdate("INSERT INTO gp (nome) VALUES (:nome)")
    @GetGeneratedKeys
    long inserir(@BindBean Pessoa gp);

    @SqlUpdate("UPDATE gp SET endereco = :idEndereco WHERE id = :id")
    void atualizarEndereco(@Bind("id") long id, @Bind("idEndereco") long idEndereco);
}
