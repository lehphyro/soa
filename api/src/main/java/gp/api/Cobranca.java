package gp.api;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.google.common.base.Objects;
import gp.api.internal.Identificado;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@JsonPropertyOrder({ "id", "tipo", "periodo", "valor" })
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Cobranca {

    @JsonProperty
    private final long id;

    @JsonProperty
    private final Gp gp;

    @JsonProperty
    @Min(0)
    private final int valor;

    @JsonProperty
    @Min(0)
    private final float periodo;

    @JsonProperty
    @NotNull
    private final Tipo tipo;

    public Cobranca(@JsonProperty("valor") int valor, @JsonProperty("periodo") float periodo, @JsonProperty("tipo") Tipo tipo) {
        this(new Builder().valor(valor).periodo(periodo).tipo(tipo));
    }

    private Cobranca(Builder builder) {
        id = builder.id;
        gp = builder.gp;
        valor = builder.valor;
        periodo = builder.periodo;
        tipo = builder.tipo;
    }

    public long getId() {
        return id;
    }

    public Gp getGp() {
        return gp;
    }

    public int getValor() {
        return valor;
    }

    public float getPeriodo() {
        return periodo;
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
                .add("periodo", periodo)
                .add("tipo", tipo)
                .toString();
    }

    public static enum Tipo implements Identificado {
        HORA(1), HORA_COMPLETA(2), PERNOITE(3), CASAL(4);

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

        Gp gp;

        int valor;

        float periodo;

        Cobranca.Tipo tipo;

        public Builder() {
        }

        public Builder(Cobranca cobranca) {
            id = cobranca.id;
            gp = cobranca.gp;
            valor = cobranca.valor;
            periodo = cobranca.periodo;
            tipo = cobranca.tipo;
        }

        public Builder id(long id) {
            this.id = id;
            return this;
        }

        public Builder gp(Gp gp) {
            this.gp = gp;
            return this;
        }

        public Builder valor(int valor) {
            this.valor = valor;
            return this;
        }

        public Builder periodo(float periodo) {
            this.periodo = periodo;
            return this;
        }

        public Builder tipo(Cobranca.Tipo tipo) {
            this.tipo = tipo;
            return this;
        }

        public Cobranca build() {
            return new Cobranca(this);
        }
    }
}
