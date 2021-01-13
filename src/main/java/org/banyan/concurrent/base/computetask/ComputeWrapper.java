package org.banyan.concurrent.base.computetask;

import java.util.concurrent.*;

/**
 * 计算结果缓存
 * User : krisjin
 * Date: 2015/9/15
 */
public class ComputeWrapper<A, V> implements Computable<A, V> {
    private final ConcurrentMap<A, Future<V>> cache = new ConcurrentHashMap<A, Future<V>>();
    private final Computable<A, V> c;

    public ComputeWrapper(Computable<A, V> c) {
        this.c = c;
    }

    @Override
    public V compute(final A arg) throws InterruptedException, ExecutionException {
        while (true) {
            Future<V> f = cache.get(arg);
            if (f == null) {
                Callable<V> callable = new Callable<V>() {
                    @Override
                    public V call() throws Exception {
                        return c.compute(arg);
                    }
                };
                FutureTask<V> futureTask = new FutureTask<V>(callable);
                f = cache.putIfAbsent(arg, futureTask);
                if (f == null) {
                    f = futureTask;
                    futureTask.run();
                }
                return f.get();
            } else {
                System.out.println("###################cache hit###################");
                return f.get();
            }

        }
    }

}
