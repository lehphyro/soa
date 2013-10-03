package discovery;

import com.datasift.dropwizard.curator.CuratorFactory;
import com.netflix.curator.framework.CuratorFramework;
import com.yammer.dropwizard.ConfiguredBundle;
import com.yammer.dropwizard.config.Bootstrap;
import com.yammer.dropwizard.config.Environment;

import discovery.health.CuratorConnectionStateListener;
import discovery.health.DescobridorHealthCheck;
import discovery.rest.mapper.NenhumaInstanciaEncontradaMapper;

public class BundleDescobertaServico implements ConfiguredBundle<DescobertaServicoConfigurada> {

	private CuratorFramework curatorFramework;

	@Override
	public void initialize(Bootstrap<?> bootstrap) {
	}

	@Override
	public void run(DescobertaServicoConfigurada configuration, Environment environment) throws Exception {
		curatorFramework = new CuratorFactory(environment).build(configuration.getCurator());
		CuratorConnectionStateListener connectionStateListener = new CuratorConnectionStateListener();
		curatorFramework.getConnectionStateListenable().addListener(connectionStateListener);

        String versao = getClass().getPackage().getImplementationVersion();
        if (versao == null) {
            versao = "dev";
        }
        PublicadorServico publicadorServico = new PublicadorServico(environment.getName(), versao, configuration.getDescricao(), curatorFramework);
        environment.addServerLifecycleListener(new DescobertaServerLifecycleListener(publicadorServico));
		environment.addHealthCheck(new DescobridorHealthCheck(connectionStateListener));
		environment.manage(publicadorServico);

        environment.addProvider(NenhumaInstanciaEncontradaMapper.class);
	}

    public DescobridorServico getDescobridorServico(ConfiguracaoAcessoServico configuracao) {
        return new DescobridorServico(configuracao.getNome(), configuracao.getVersao(), configuracao.isUsarCache(), curatorFramework);
    }
}
