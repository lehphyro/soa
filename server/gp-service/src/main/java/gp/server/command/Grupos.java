package gp.server.command;

import com.netflix.hystrix.HystrixCommandGroupKey;

public enum Grupos implements HystrixCommandGroupKey {
    GP, COBRANCA, CONTATO, ENDERECO, WEBSITE_DIVULGACAO, WEBSITE_FORUM, WEBSITE_PESSOAL
}
