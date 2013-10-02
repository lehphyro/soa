package gp.server;

import gp.database.Constantes;
import gp.discovery.BundleDescobertaServico;
import gp.server.integracao.BundleIntegracao;
import gp.server.resource.GpResource;

import org.skife.jdbi.v2.DBI;

import com.sun.jersey.api.client.Client;
import com.yammer.dropwizard.Service;
import com.yammer.dropwizard.client.JerseyClientBuilder;
import com.yammer.dropwizard.config.Bootstrap;
import com.yammer.dropwizard.config.Environment;
import com.yammer.dropwizard.db.DatabaseConfiguration;
import com.yammer.dropwizard.jdbi.DBIFactory;
import com.yammer.dropwizard.migrations.MigrationsBundle;

public class GpService extends Service<GpConfiguration> {

	public static final String NOME = "gp";

	private BundleDescobertaServico descobertaServico;

	@Override
	public void initialize(Bootstrap<GpConfiguration> bootstrap) {
		bootstrap.setName(NOME);
		bootstrap.addBundle(new MigrationsBundle<GpConfiguration>() {
			@Override
			public DatabaseConfiguration getDatabaseConfiguration(GpConfiguration configuration) {
				return configuration.getDatabaseConfiguration();
			}
		});
		descobertaServico = new BundleDescobertaServico();
		bootstrap.addBundle(descobertaServico);
		bootstrap.addBundle(new BundleIntegracao());
	}

	@Override
	public void run(GpConfiguration configuration, Environment environment) throws Exception {
		DBIFactory dbiFactory = new DBIFactory();
		DBI dbi = dbiFactory.build(environment, configuration.getDatabaseConfiguration(), Constantes.POSTGRESQL);
		Client client = new JerseyClientBuilder().using(configuration.getHttpClient()).using(environment).build();
		environment.addResource(new GpResource(dbi, client, descobertaServico.getDescobridorServico(configuration.getServicoEndereco()),
				descobertaServico.getDescobridorServico(configuration.getServicoContato()), descobertaServico
						.getDescobridorServico(configuration.getServicoCobranca())));
	}

	public static void main(String[] args) throws Exception {
		new GpService().run(args);
	}
}
