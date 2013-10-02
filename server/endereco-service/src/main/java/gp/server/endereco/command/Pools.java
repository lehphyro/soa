package gp.server.endereco.command;

import com.netflix.hystrix.HystrixThreadPoolKey;

public enum Pools implements HystrixThreadPoolKey {
    DATABASE
}
