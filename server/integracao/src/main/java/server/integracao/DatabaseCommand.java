package server.integracao;

import org.skife.jdbi.v2.DBI;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixThreadPoolKey;

public abstract class DatabaseCommand<T> extends HystrixCommand<T> {

    protected final DBI dbi;

	protected DatabaseCommand(HystrixCommandGroupKey groupKey, DBI dbi) {
		super(Setter.withGroupKey(groupKey).andThreadPoolKey(HystrixThreadPoolKey.Factory.asKey("database")));
        this.dbi = dbi;
    }
}
