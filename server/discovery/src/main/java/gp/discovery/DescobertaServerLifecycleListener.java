package gp.discovery;

import com.yammer.dropwizard.lifecycle.ServerLifecycleListener;
import org.eclipse.jetty.server.Connector;
import org.eclipse.jetty.server.Server;

class DescobertaServerLifecycleListener implements ServerLifecycleListener {

    private final PublicadorServico publicadorServico;

    public DescobertaServerLifecycleListener(PublicadorServico publicadorServico) {
        this.publicadorServico = publicadorServico;
    }

    @Override
    public void serverStarted(Server server) {
        for (Connector connector : server.getConnectors()) {
            if ("main".equals(connector.getName())) {
                publicadorServico.publicar(connector.getLocalPort());
            }
        }
    }
}
