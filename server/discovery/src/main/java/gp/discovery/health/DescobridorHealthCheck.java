package gp.discovery.health;

import com.netflix.curator.framework.state.ConnectionState;
import com.yammer.metrics.core.HealthCheck;

public class DescobridorHealthCheck extends HealthCheck {

	private final CuratorConnectionStateListener connectionStateListener;

	public DescobridorHealthCheck(CuratorConnectionStateListener connectionStateListener) {
		super("descoberta-servico");
		this.connectionStateListener = connectionStateListener;
	}

	@Override
	protected Result check() throws Exception {
		Result result;
		ConnectionState state = connectionStateListener.getCurrentState();
		switch (state) {
			case CONNECTED:
			case RECONNECTED:
				result = Result.healthy();
				break;
			case READ_ONLY:
				result = Result.healthy("A conexao esta em modo somente-leitura");
				break;
			case SUSPENDED:
				result = Result.unhealthy("A conexao foi suspensa, tentando reconectar");
				break;
			case LOST:
				result = Result.unhealthy("A conexao foi perdida, tentando reconectar");
				break;
			default:
				result = Result.unhealthy("Estado da conexao desconhecido: %s. E necessario atualizar o codigo de health check.", state);
				break;
		}
		return result;
	}
}
