package endereco.service.repository;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.BindBean;
import org.skife.jdbi.v2.sqlobject.GetGeneratedKeys;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.Mapper;
import org.skife.jdbi.v2.sqlobject.mixins.Transactional;

import endereco.api.Endereco;

public interface EnderecoRepository extends Transactional<EnderecoRepository>, AutoCloseable {

    @SqlQuery("SELECT logradouro, numero, complemento, bairro FROM endereco WHERE id = :id")
    @Mapper(EnderecoMapper.class)
    Endereco recuperar(@Bind("id") long id);

    @SqlUpdate("INSERT INTO endereco (logradouro, numero, complemento, bairro) VALUES (:logradouro, :numero, :complemento, :bairro)")
    @GetGeneratedKeys
    long inserir(@BindBean Endereco endereco);

}
