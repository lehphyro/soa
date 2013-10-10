package contato.api;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.google.common.base.Objects;
import com.google.common.base.Optional;

@JsonPropertyOrder({ "id", "tipo", "valor" })
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Contato {

    @JsonProperty
    private final long id;

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
        valor = builder.valor;
        tipo = builder.tipo;
    }

    public long getId() {
        return id;
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
                .add("valor", valor)
                .add("tipo", tipo)
                .toString();
    }

	public static enum Tipo {
        TELEFONE(1), EMAIL(2), IM(3);

        private final int id;

        private Tipo(int id) {
            this.id = id;
        }

		public static Optional<Tipo> valueOf(int i) {
			for (Tipo tipo : values()) {
				if (tipo.id == i) {
					return Optional.of(tipo);
				}
			}
			return Optional.absent();
		}

        public int getId() {
            return id;
        }
    }

    public static class Builder {

        long id;

        String valor;

        Tipo tipo;

        public Builder() {
        }

        public Builder(Contato v) {
            this.id = v.id;
            this.valor = v.valor;
            this.tipo = v.tipo;

        }

        public Builder id(long v) {
            this.id = v;
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
