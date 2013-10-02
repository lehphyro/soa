package gp.server.integracao;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixThreadPoolKey;
import org.skife.jdbi.v2.DBI;

public abstract class DatabaseCommand<T> extends HystrixCommand<T> {

    protected final DBI dbi;

    protected DatabaseCommand(HystrixCommandGroupKey groupKey, HystrixThreadPoolKey poolKey, DBI dbi) {
        super(Setter.withGroupKey(groupKey).andThreadPoolKey(poolKey));
        this.dbi = dbi;
    }
}
