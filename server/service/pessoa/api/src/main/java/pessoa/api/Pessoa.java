package pessoa.api;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.google.common.base.Objects;
import com.google.common.collect.ImmutableList;

import contato.api.Contato;
import endereco.api.Endereco;

@JsonPropertyOrder({ "id", "nome", "contatos", "endereco" })
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Pessoa {

    @JsonProperty
    private final long id;

    @JsonProperty
    @NotEmpty
    private final String nome;

    @JsonProperty
    private final List<Contato> contatos;

    @JsonProperty
    private final Endereco endereco;

    public Pessoa(@JsonProperty("nome") String nome) {
        this(new Builder().nome(nome));
    }

    private Pessoa(Builder builder) {
        id = builder.id;
        nome = builder.nome;
        contatos = ImmutableList.copyOf(builder.contatos);
        endereco = builder.endereco;
    }

    public static class Builder {
        long id;
        String nome;
        List<Contato> contatos = new ArrayList<>();
        Endereco endereco;

        public Builder() {
        }

        public Builder(Pessoa pessoa) {
            id = pessoa.id;
            nome = pessoa.nome;
            contatos = new ArrayList<>(pessoa.contatos);
            endereco = pessoa.endereco;
        }

        public Builder id(long id) {
            this.id = id;
            return this;
        }

        public Builder nome(String nome) {
            this.nome = nome;
            return this;
        }

        public Builder addContato(Contato v) {
            this.contatos.add(v);
            return this;
        }

        public Builder contatos(Collection<? extends Contato> v) {
            this.contatos.addAll(v);
            return this;
        }

        public Builder endereco(Endereco endereco) {
            this.endereco = endereco;
            return this;
        }

        public Pessoa build() {
            return new Pessoa(this);
        }
    }

    public long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public List<Contato> getContatos() {
        return contatos;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(this)
                .add("id", id)
                .add("nome", nome)
                .add("contatos", contatos)
                .add("endereco", endereco)
                .toString();
    }
}
