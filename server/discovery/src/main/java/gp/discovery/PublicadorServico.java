package gp.discovery;

import com.netflix.curator.x.discovery.*;
import gp.discovery.zookeeper.Nomeador;

import com.google.common.io.Closeables;
import com.netflix.curator.framework.CuratorFramework;
import com.netflix.curator.x.discovery.details.JsonInstanceSerializer;
import com.yammer.dropwizard.lifecycle.Managed;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;

class PublicadorServico implements Managed {

    private static final Logger logger = LoggerFactory.getLogger(PublicadorServico.class);

    static {
        ServiceInstanceBuilder.setLocalIpFilter(new LocalIpFilter() {
            @Override
            public boolean use(NetworkInterface networkInterface, InetAddress address) throws SocketException {
                try {
                    return (address != null) &&
                            !address.isLoopbackAddress() &&
                            (networkInterface.isPointToPoint() || !address.isLinkLocalAddress()) &&
                            !(address instanceof Inet6Address);
                } catch (SocketException e) {
                    return false;
                }
            }
        });
    }

    private final String nome;

    private final String versao;

    private final DetalhesServico detalhesServico;

    private final ServiceDiscovery<DetalhesServico> serviceDiscovery;

    public PublicadorServico(String nome, String versao, String descricao, CuratorFramework client) {
        this.nome = nome;
        this.versao = versao;
        this.detalhesServico = new DetalhesServico(descricao);

        JsonInstanceSerializer<DetalhesServico> serializer = new JsonInstanceSerializer<>(DetalhesServico.class);
        serviceDiscovery = ServiceDiscoveryBuilder.builder(DetalhesServico.class).client(client).basePath("/").serializer(serializer).build();
    }

    public void publicar(int portaHttp) {
        UriSpec uriSpec = new UriSpec("http://{address}:{port}");
        try {
            ServiceInstance<DetalhesServico> essaInstancia = ServiceInstance.<DetalhesServico>builder()
                    .name(Nomeador.montarNomeComVersao(nome, versao))
                    .payload(detalhesServico).port(portaHttp)
                    .uriSpec(uriSpec).build();
            serviceDiscovery.registerService(essaInstancia);
        } catch (Exception e) {
            logger.warn("Nao foi possivel publicar o servico, outros servicos nao vao encontra-lo", e);
        }
    }

    @Override
    public void start() throws Exception {
        serviceDiscovery.start();
    }

    @Override
    public void stop() throws Exception {
        Closeables.close(serviceDiscovery, false);
    }
}
