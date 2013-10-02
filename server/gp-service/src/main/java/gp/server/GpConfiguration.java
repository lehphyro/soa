package gp.server;

import com.fasterxml.jackson.annotation.JsonProperty;
import gp.configuracao.ConfiguracaoBase;
import gp.discovery.ConfiguracaoAcessoServico;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public class GpConfiguration extends ConfiguracaoBase {

    @Valid
    @NotNull
    @JsonProperty
    private ConfiguracaoAcessoServico servicoEndereco;

    @Valid
    @NotNull
    @JsonProperty
    private ConfiguracaoAcessoServico servicoContato;

    @Valid
    @NotNull
    @JsonProperty
    private ConfiguracaoAcessoServico servicoCobranca;

    @Valid
    @NotNull
    @JsonProperty
    private ConfiguracaoAcessoServico servicoWebsiteDivulgacao;

    @Valid
    @NotNull
    @JsonProperty
    private ConfiguracaoAcessoServico servicoWebsiteForum;

    @Valid
    @NotNull
    @JsonProperty
    private ConfiguracaoAcessoServico servicoWebsitePessoal;

    public ConfiguracaoAcessoServico getServicoEndereco() {
        return servicoEndereco;
    }

    public ConfiguracaoAcessoServico getServicoContato() {
        return servicoContato;
    }

    public ConfiguracaoAcessoServico getServicoCobranca() {
        return servicoCobranca;
    }

    public ConfiguracaoAcessoServico getServicoWebsiteDivulgacao() {
        return servicoWebsiteDivulgacao;
    }

    public ConfiguracaoAcessoServico getServicoWebsiteForum() {
        return servicoWebsiteForum;
    }

    public ConfiguracaoAcessoServico getServicoWebsitePessoal() {
        return servicoWebsitePessoal;
    }
}
