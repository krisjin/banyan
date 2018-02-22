package org.concurrent.threadpool;


import org.concurrent.thread.pool.NamedThreadFactory;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 调度线程池使用
 * @author shijingui on 2017/5/20
 */
public class UsingScheduledThreadPool {

    public static void main(String[] args) {
        testThreadPool2();
    }


    public static void testThread() {
        long st = System.currentTimeMillis();
        for (int i = 0; i < 1000; i++) {
            new Thread(new MyThread("testNoThreadPool" + i)).start();
        }
        long cost = System.currentTimeMillis() - st;
        System.out.println("cost:" + cost + " ms");
    }


    public static void testThreadPool() {
        long st = System.currentTimeMillis();
        for (int i = 0; i < 1000; i++) {
            ThreadPool.getInstance().start(new MyThread("TestThreadPool" + i));
        }
        long cost = System.currentTimeMillis() - st;
        System.out.println("cost:" + cost + " ms");
    }

    public static void testThreadPool2() {
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(2, new NamedThreadFactory("Task-1"));

        scheduledExecutorService.scheduleWithFixedDelay(new Runnable() {//测试scheduleAtFixedRate
            AtomicInteger counter = new AtomicInteger(0);

            @Override
            public void run() {
                System.err.println("执行" + counter.incrementAndGet() + "次");
            }
        }, 3000, 2000, TimeUnit.MILLISECONDS);


        scheduledExecutorService.scheduleWithFixedDelay(new Runnable() {//测试scheduleWithFixedDelay
            AtomicInteger counter = new AtomicInteger(0);

            @Override
            public void run() {
                System.err.println("abc执行" + counter.incrementAndGet() + "次");
            }
        }, 3000, 2000, TimeUnit.MILLISECONDS);
    }
}
