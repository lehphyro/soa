package gp.discovery;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.NotEmpty;

public class ConfiguracaoAcessoServico {

    @NotEmpty
    @JsonProperty
    private String nome;

    @NotEmpty
    @JsonProperty
    private String versao;

    @SuppressWarnings("FieldCanBeLocal")
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
