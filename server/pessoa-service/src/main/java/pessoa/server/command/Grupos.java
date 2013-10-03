package pessoa.server.command;

import com.netflix.hystrix.HystrixCommandGroupKey;

public enum Grupos implements HystrixCommandGroupKey {
	PESSOA,
	CONTATO,
	ENDERECO
}
