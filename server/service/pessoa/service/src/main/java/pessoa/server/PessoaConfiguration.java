package pessoa.server;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

import configuracao.ConfiguracaoBase;
import discovery.ConfiguracaoAcessoServico;

public class PessoaConfiguration extends ConfiguracaoBase {

    @Valid
    @NotNull
    @JsonProperty
    private ConfiguracaoAcessoServico servicoEndereco;

    @Valid
    @NotNull
    @JsonProperty
    private ConfiguracaoAcessoServico servicoContato;

    public ConfiguracaoAcessoServico getServicoEndereco() {
        return servicoEndereco;
    }

    public ConfiguracaoAcessoServico getServicoContato() {
        return servicoContato;
    }
}
