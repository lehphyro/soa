package endereco.server.command;

import com.netflix.hystrix.HystrixCommandGroupKey;

public enum Grupos implements HystrixCommandGroupKey {
    ENDERECO
}
