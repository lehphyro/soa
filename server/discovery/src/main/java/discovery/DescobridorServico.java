package discovery;

import java.util.Collection;

import com.google.common.io.Closeables;
import com.netflix.curator.framework.CuratorFramework;
import com.netflix.curator.x.discovery.ServiceCache;
import com.netflix.curator.x.discovery.ServiceDiscovery;
import com.netflix.curator.x.discovery.ServiceDiscoveryBuilder;
import com.netflix.curator.x.discovery.ServiceInstance;
import com.netflix.curator.x.discovery.details.JsonInstanceSerializer;
import com.yammer.dropwizard.lifecycle.Managed;

import discovery.zookeeper.Nomeador;

public class DescobridorServico implements Managed {

	private final String nomeComVersao;
	private final boolean usarCache;

	private final ServiceDiscovery<DetalhesServico> serviceDiscovery;
	private final ServiceCache<DetalhesServico> serviceCache;

	public DescobridorServico(String nome, String versao, boolean usarCache, CuratorFramework client) {
		this.nomeComVersao = Nomeador.montarNomeComVersao(nome, versao);
		this.usarCache = usarCache;

		JsonInstanceSerializer<DetalhesServico> serializer = new JsonInstanceSerializer<>(DetalhesServico.class);
		serviceDiscovery = ServiceDiscoveryBuilder.builder(DetalhesServico.class).client(client).basePath("/").serializer(serializer).build();
		if (usarCache) {
			serviceCache = serviceDiscovery.serviceCacheBuilder().name(nomeComVersao).build();
		} else {
			serviceCache = null;
		}
	}

	@Override
	public void start() throws Exception {
		serviceDiscovery.start();
		if (usarCache) {
			serviceCache.start();
		}
	}

	@Override
	public void stop() throws Exception {
		if (usarCache) {
			Closeables.close(serviceCache, false);
		}
		Closeables.close(serviceDiscovery, false);
	}

	public ServiceInstance<DetalhesServico> getServico() throws NenhumaInstanciaEncontradaException {
		Collection<ServiceInstance<DetalhesServico>> instancias;
		if (usarCache) {
			instancias = serviceCache.getInstances();
		} else {
			try {
				instancias = serviceDiscovery.queryForInstances(nomeComVersao);
			} catch (Exception e) {
				throw new NenhumaInstanciaEncontradaException(nomeComVersao, e);
			}
		}
		if (instancias.isEmpty()) {
			throw new NenhumaInstanciaEncontradaException(nomeComVersao);
		}
		return instancias.iterator().next();
	}
}
