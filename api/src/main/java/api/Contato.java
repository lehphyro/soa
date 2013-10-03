package api;

import api.internal.Identificado;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.google.common.base.Objects;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;

@JsonPropertyOrder({ "id", "tipo", "valor" })
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Contato {

    @JsonProperty
    private final long id;

    @JsonProperty
    private final Pessoa gp;

    @JsonProperty
    @NotEmpty
    private final String valor;

    @JsonProperty
    @NotNull
    private final Tipo tipo;

    public Contato(@JsonProperty("valor") String valor, @JsonProperty("tipo") Tipo tipo) {
        this(new Builder().valor(valor).tipo(tipo));
    }

    private Contato(Builder builder) {
        id = builder.id;
        gp = builder.gp;
        valor = builder.valor;
        tipo = builder.tipo;
    }

    public long getId() {
        return id;
    }

    public Pessoa getGp() {
        return gp;
    }

    public String getValor() {
        return valor;
    }

    public Tipo getTipo() {
        return tipo;
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(this)
                .add("id", id)
                .add("gp", gp)
                .add("valor", valor)
                .add("tipo", tipo)
                .toString();
    }

    public static enum Tipo implements Identificado {
        TELEFONE(1), EMAIL(2), IM(3);

        private final int id;

        private Tipo(int id) {
            this.id = id;
        }

        @Override
        public int getId() {
            return id;
        }
    }

    public static class Builder {

        long id;

        Pessoa gp;

        String valor;

        Tipo tipo;

        public Builder() {
        }

        public Builder(Contato v) {
            this.id = v.id;
            this.gp = v.gp;
            this.valor = v.valor;
            this.tipo = v.tipo;

        }

        public Builder id(long v) {
            this.id = v;
            return this;
        }

        public Builder gp(Pessoa v) {
            this.gp = v;
            return this;
        }

        public Builder valor(String v) {
            this.valor = v;
            return this;
        }

        public Builder tipo(Tipo v) {
            this.tipo = v;
            return this;
        }

        public Contato build() {
            return new Contato(this);
        }
    }
}
