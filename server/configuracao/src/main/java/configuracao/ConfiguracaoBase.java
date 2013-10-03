package configuracao;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import com.datasift.dropwizard.curator.config.CuratorConfiguration;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.yammer.dropwizard.client.JerseyClientConfiguration;
import com.yammer.dropwizard.config.Configuration;
import com.yammer.dropwizard.db.DatabaseConfiguration;

import discovery.DescobertaServicoConfigurada;

public class ConfiguracaoBase extends Configuration implements DescobertaServicoConfigurada {

    @NotEmpty
    @JsonProperty
    private String descricao = "Sem descricao";

    @Valid
    @NotNull
    @JsonProperty
    private CuratorConfiguration curator;

    @Valid
    @NotNull
    @JsonProperty
    private DatabaseConfiguration database = new DatabaseConfiguration();

    @Valid
    @NotNull
    @JsonProperty
    private JerseyClientConfiguration httpClient = new JerseyClientConfiguration();

    @Override
    public String getDescricao() {
        return descricao;
    }

    @Override
    public CuratorConfiguration getCurator() {
        return curator;
    }

    public DatabaseConfiguration getDatabaseConfiguration() {
        return database;
    }

    public JerseyClientConfiguration getHttpClient() {
        return httpClient;
    }
}
