package pessoa.server.repository;

import java.util.List;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.BindBean;
import org.skife.jdbi.v2.sqlobject.GetGeneratedKeys;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;
import org.skife.jdbi.v2.sqlobject.mixins.Transactional;

import api.Pessoa;

@RegisterMapper(PessoaMapper.class)
public interface PessoaRepository extends Transactional<PessoaRepository>, AutoCloseable {
	@SqlQuery("SELECT id, nome FROM pessoa")
    List<Pessoa> findAll();

	@SqlQuery("SELECT id, nome FROM pessoa WHERE id = :id")
    Pessoa recuperar(@Bind("id") long id);

	@SqlUpdate("INSERT INTO pessoa (nome) VALUES (:nome)")
    @GetGeneratedKeys
	long inserir(@BindBean Pessoa pessoa);

	@SqlUpdate("UPDATE pessoa SET endereco = :idEndereco WHERE id = :id")
    void atualizarEndereco(@Bind("id") long id, @Bind("idEndereco") long idEndereco);
}
