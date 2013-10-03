package discovery;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ConfiguracaoAcessoServico {

    @NotEmpty
    @JsonProperty
    private String nome;

    @NotEmpty
    @JsonProperty
    private String versao;

    @JsonProperty
    private boolean usarCache = false;

    public String getNome() {
        return nome;
    }

    public String getVersao() {
        return versao;
    }

    public boolean isUsarCache() {
        return usarCache;
    }
}
