package org.banyan.concurrent.base.computetask;

import java.util.concurrent.*;

/**
 * 计算结果缓存包装器
 * User : krisjin
 * Date: 2015/9/15
 */
public class ComputeWrapper<A, V> implements Computable<A, V> {
    //结果缓存
    private final ConcurrentMap<A, Future<V>> cache = new ConcurrentHashMap<A, Future<V>>();
    //计算接口
    private final Computable<A, V> computable;

    //包装器传入计算接口
    public ComputeWrapper(Computable<A, V> computable) {
        this.computable = computable;
    }

    @Override
    public V compute(final A arg) throws InterruptedException, ExecutionException {
        while (true) {
            Future<V> future = cache.get(arg);
            if (future == null) {
                Callable<V> callable = new Callable<V>() {
                    public V call() throws Exception {
                        return computable.compute(arg);
                    }
                };
                FutureTask<V> futureTask = new FutureTask<V>(callable);
                future = cache.putIfAbsent(arg, futureTask);
                if (future == null) {
                    future = futureTask;
                    futureTask.run();
                }
                return future.get();
            } else {
                System.out.println("###################cache hit###################");
                return future.get();
            }
        }
    }

}
