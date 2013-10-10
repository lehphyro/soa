package contato.server.command;

import com.netflix.hystrix.HystrixThreadPoolKey;

public enum Pools implements HystrixThreadPoolKey {
    DATABASE
}
