package gp.server.cobranca.command;

import com.netflix.hystrix.HystrixThreadPoolKey;

public enum Pools implements HystrixThreadPoolKey {
    DATABASE
}
