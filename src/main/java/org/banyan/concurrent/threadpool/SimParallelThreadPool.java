package org.banyan.concurrent.threadpool;

import org.banyan.concurrent.thread.pool.NamedThreadFactory;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class SimParallelThreadPool {

    public static Integer threadNum = 20;

    public void setThreadNum(Integer threadNum) {
        SimParallelThreadPool.threadNum = threadNum;
    }

    private static ThreadFactory threadFactory = new NamedThreadFactory("BM25");

    private static volatile ThreadPoolExecutor threadPoolExecutor = null;

    //Init threadpool
    public static ThreadPoolExecutor getThreadPool() {
        if (threadPoolExecutor == null) {
            synchronized (SimParallelThreadPool.class) {
                if (threadPoolExecutor == null) {
                    threadPoolExecutor = new ThreadPoolExecutor(threadNum, threadNum,
                            30L, TimeUnit.SECONDS, new LinkedBlockingQueue<>(5000), threadFactory);
                }
            }
        }
        return threadPoolExecutor;
    }

    //Shut down threadPool
    public void shutDownThreadPool() {
        if (threadPoolExecutor != null) {
            threadPoolExecutor.shutdown();
        }
    }
}
