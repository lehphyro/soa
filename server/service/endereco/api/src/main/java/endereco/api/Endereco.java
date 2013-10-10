package endereco.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.Objects;
import org.hibernate.validator.constraints.NotEmpty;

public class Endereco {

    @JsonProperty
    private final long id;

    @JsonProperty
    @NotEmpty
    private final String logradouro;

    @JsonProperty
    @NotEmpty
    private final String numero;

    @JsonProperty
    private final String complemento;

    @JsonProperty
    @NotEmpty
    private final String bairro;

    public Endereco(@JsonProperty("logradouro") String logradouro, @JsonProperty("numero") String numero, @JsonProperty("complemento") String complemento, @JsonProperty("bairro") String bairro) {
        this(new Builder().logradouro(logradouro).numero(numero).complemento(complemento).bairro(bairro));
    }

    private Endereco(Builder builder) {
        id = builder.id;
        logradouro = builder.logradouro;
        numero = builder.numero;
        complemento = builder.complemento;
        bairro = builder.bairro;
    }

    public long getId() {
        return id;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public String getNumero() {
        return numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public String getBairro() {
        return bairro;
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(this)
                .add("id", id)
                .add("logradouro", logradouro)
                .add("numero", numero)
                .add("complemento", complemento)
                .add("bairro", bairro)
                .toString();
    }

    public static class Builder {
        long id;

        String logradouro;

        String numero;

        String complemento;

        String bairro;

        public Builder id(long id) {
            this.id = id;
            return this;
        }

        public Builder logradouro(String logradouro) {
            this.logradouro = logradouro;
            return this;
        }

        public Builder numero(String numero) {
            this.numero = numero;
            return this;
        }

        public Builder complemento(String complemento) {
            this.complemento = complemento;
            return this;
        }

        public Builder bairro(String bairro) {
            this.bairro = bairro;
            return this;
        }

        public Endereco build() {
            return new Endereco(this);
        }
    }
}
