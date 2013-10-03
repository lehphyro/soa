package server.contato.repository;

import java.util.List;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.GetGeneratedKeys;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.Mapper;
import org.skife.jdbi.v2.sqlobject.mixins.Transactional;

import api.Contato;
import database.binding.BindDeepBean;

public interface ContatoRepository extends Transactional<ContatoRepository>, AutoCloseable {

	@SqlQuery("SELECT id, tipo, valor FROM contato WHERE pessoa = :pessoa")
    @Mapper(ContatoMapper.class)
	List<Contato> listarPorPessoa(@Bind("pessoa") long pessoa);

	@SqlUpdate("INSERT INTO contato (tipo, pessoa, valor) VALUES (:tipo, :pessoa.id, :valor)")
    @GetGeneratedKeys
    long inserir(@BindDeepBean Contato contato);

}
