package pessoa.service;

import org.skife.jdbi.v2.DBI;

import pessoa.service.resource.PessoaResource;
import server.integracao.BundleIntegracao;

import com.sun.jersey.api.client.Client;
import com.yammer.dropwizard.Service;
import com.yammer.dropwizard.client.JerseyClientBuilder;
import com.yammer.dropwizard.config.Bootstrap;
import com.yammer.dropwizard.config.Environment;
import com.yammer.dropwizard.db.DatabaseConfiguration;
import com.yammer.dropwizard.jdbi.DBIFactory;
import com.yammer.dropwizard.migrations.MigrationsBundle;

import discovery.BundleDescobertaServico;

public class PessoaService extends Service<PessoaConfiguration> {

	public static final String NOME = "pessoa";

	private BundleDescobertaServico descobertaServico;

	@Override
	public void initialize(Bootstrap<PessoaConfiguration> bootstrap) {
		bootstrap.setName(NOME);
		bootstrap.addBundle(new MigrationsBundle<PessoaConfiguration>() {
			@Override
			public DatabaseConfiguration getDatabaseConfiguration(PessoaConfiguration configuration) {
				return configuration.getDatabaseConfiguration();
			}
		});
		descobertaServico = new BundleDescobertaServico();
		bootstrap.addBundle(descobertaServico);
		bootstrap.addBundle(new BundleIntegracao());
	}

	@Override
	public void run(PessoaConfiguration configuration, Environment environment) throws Exception {
		DBIFactory dbiFactory = new DBIFactory();
		DBI dbi = dbiFactory.build(environment, configuration.getDatabaseConfiguration(), "postgresql");
		Client client = new JerseyClientBuilder().using(configuration.getHttpClient()).using(environment).build();
		environment.addResource(new PessoaResource(dbi, client, descobertaServico.getDescobridorServico(configuration.getServicoEndereco()),
				descobertaServico.getDescobridorServico(configuration.getServicoContato())));
	}

	public static void main(String[] args) throws Exception {
		new PessoaService().run(args);
	}
}
