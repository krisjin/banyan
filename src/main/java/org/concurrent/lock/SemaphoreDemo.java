package org.concurrent.lock;

import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class SemaphoreDemo {
    /**
     * 最大每秒查询率
     */
    final static int MAX_QPS = 1000;
    /**
     * 信号量
     */
    final static Semaphore semaphore = new Semaphore(MAX_QPS);

    public static void main(String... args) throws Exception {
        //创建调度线程池
        Executors.newScheduledThreadPool(1).scheduleAtFixedRate(new Runnable() {
            public void run() {
                semaphore.release(MAX_QPS / 2);
            }

        }, 1000, 500, TimeUnit.MILLISECONDS);
        //lots of concurrent calls:100 * 1000

        //创建固定大小线程池
        ExecutorService pool = Executors.newFixedThreadPool(100);

        for (int i = 100; i > 0; i--) {
            final int x = i;
            pool.submit(new Runnable() {
                public void run() {
                    for (int j = 1000; j > 0; j--) {
                        semaphore.acquireUninterruptibly(1);
                        remoteCall(x, j);
                    }
                }
            });
        }
        pool.shutdown();
        pool.awaitTermination(1, TimeUnit.HOURS);
        System.out.println("DONE");
    }

    private static void remoteCall(int i, int j) {
        System.out.println(String.format("%s - %s: %d %d", new Date(), Thread.currentThread(), i, j));
    }

}