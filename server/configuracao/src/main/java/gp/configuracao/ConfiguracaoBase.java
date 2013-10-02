package gp.configuracao;

import com.datasift.dropwizard.curator.config.CuratorConfiguration;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.yammer.dropwizard.client.JerseyClientConfiguration;
import com.yammer.dropwizard.config.Configuration;
import com.yammer.dropwizard.db.DatabaseConfiguration;
import gp.discovery.DescobertaServicoConfigurada;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public class ConfiguracaoBase extends Configuration implements DescobertaServicoConfigurada {

    @SuppressWarnings("FieldCanBeLocal")
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
