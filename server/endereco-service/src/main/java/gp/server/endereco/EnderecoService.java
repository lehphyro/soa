package gp.server.endereco;

import com.yammer.dropwizard.Service;
import com.yammer.dropwizard.config.Bootstrap;
import com.yammer.dropwizard.config.Environment;
import com.yammer.dropwizard.db.DatabaseConfiguration;
import com.yammer.dropwizard.jdbi.DBIFactory;
import com.yammer.dropwizard.migrations.MigrationsBundle;
import gp.configuracao.ConfiguracaoBase;
import gp.database.Constantes;
import gp.discovery.BundleDescobertaServico;
import gp.server.endereco.resource.EnderecoResource;
import gp.server.integracao.BundleIntegracao;
import org.skife.jdbi.v2.DBI;

public class EnderecoService extends Service<ConfiguracaoBase> {

    public static final String NOME = "endereco";

    @Override
    public void initialize(Bootstrap<ConfiguracaoBase> bootstrap) {
        bootstrap.setName(NOME);
        bootstrap.addBundle(new MigrationsBundle<ConfiguracaoBase>() {
            @Override
            public DatabaseConfiguration getDatabaseConfiguration(ConfiguracaoBase configuration) {
                return configuration.getDatabaseConfiguration();
            }
        });
        bootstrap.addBundle(new BundleDescobertaServico());
        bootstrap.addBundle(new BundleIntegracao());
    }

    @Override
    public void run(ConfiguracaoBase configuration, Environment environment) throws Exception {
        DBIFactory dbiFactory = new DBIFactory();
        DBI dbi = dbiFactory.build(environment, configuration.getDatabaseConfiguration(), Constantes.POSTGRESQL);
        environment.addResource(new EnderecoResource(dbi));
    }

    public static void main(String[] args) throws Exception {
        new EnderecoService().run(args);
    }
}
