package gp.server.command;

import com.netflix.hystrix.HystrixThreadPoolKey;

public enum Pools implements HystrixThreadPoolKey {
    DATABASE, COBRANCA, CONTATO, ENDERECO, WEBSITE_DIVULGACAO, WEBSITE_FORUM, WEBSITE_PESSOAL
}
