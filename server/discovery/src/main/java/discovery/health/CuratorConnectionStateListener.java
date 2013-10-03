package discovery.health;

import java.util.concurrent.atomic.AtomicReference;

import com.netflix.curator.framework.CuratorFramework;
import com.netflix.curator.framework.state.ConnectionState;
import com.netflix.curator.framework.state.ConnectionStateListener;

public class CuratorConnectionStateListener implements ConnectionStateListener {

	private final AtomicReference<ConnectionState> currentState = new AtomicReference<>(ConnectionState.LOST);

	@Override
	public void stateChanged(CuratorFramework client, ConnectionState newState) {
		currentState.set(newState);
	}

	public ConnectionState getCurrentState() {
		return currentState.get();
	}
}
