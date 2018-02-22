package org.concurrent.threadpool;


import org.concurrent.thread.pool.NamedThreadFactory;

import java.util.LinkedList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 调度线程池使用
 *
 * @author shijingui on 2017/5/20
 */
public class UsingExecutors {

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


    public static void usingSingleThreadExecutor() {
        System.out.println("=== SingleThreadExecutor ===");
        ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();
        singleThreadExecutor.execute(() -> System.out.println("Print this."));
        singleThreadExecutor.execute(() -> System.out.println("and this one to."));
        singleThreadExecutor.shutdown();
        try {
            singleThreadExecutor.awaitTermination(4, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("\n\n");
    }

    public static void usingCachedThreadPool() {
        System.out.println("=== CachedThreadPool ===");
        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
        List<Future<UUID>> uuids = new LinkedList<>();
        for (int i = 0; i < 10; i++) {
            Future<UUID> submitted = cachedThreadPool.submit(() -> {
                UUID randomUUID = UUID.randomUUID();
                System.out.println("UUID " + randomUUID + " from " + Thread.currentThread().getName());
                return randomUUID;
            });
            uuids.add(submitted);
        }
        cachedThreadPool.execute(() -> uuids.forEach((f) -> {
            try {
                System.out.println("Result " + f.get() + " from thread " + Thread.currentThread().getName());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }));
        cachedThreadPool.shutdown();
        try {
            cachedThreadPool.awaitTermination(4, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("\n\n");

    }

    public static void usingFixedThreadPool() {
        System.out.println("=== FixedThreadPool ===");
        ExecutorService fixedPool = Executors.newFixedThreadPool(4);
        List<Future<UUID>> uuids = new LinkedList<>();
        for (int i = 0; i < 20; i++) {
            Future<UUID> submitted = fixedPool.submit(() -> {
                UUID randomUUID = UUID.randomUUID();
                System.out.println("UUID " + randomUUID + " from " + Thread.currentThread().getName());
                return randomUUID;
            });
            uuids.add(submitted);
        }
        fixedPool.execute(() -> uuids.forEach((f) -> {
            try {
                System.out.println("Result " + f.get() + " from " + Thread.currentThread().getName());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }));
        fixedPool.shutdown();
        try {
            fixedPool.awaitTermination(4, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("\n\n");
    }

    public static void usingScheduledThreadPool() {
        System.out.println("=== ScheduledThreadPool ===");
        ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(4);
        scheduledThreadPool.scheduleAtFixedRate(
                () -> System.out.println("Print every 2s"), 0, 2, TimeUnit.SECONDS);
        scheduledThreadPool.scheduleWithFixedDelay(
                () -> System.out.println("Print every 2s delay"), 0, 2, TimeUnit.SECONDS);

        try {
            scheduledThreadPool.awaitTermination(6, TimeUnit.SECONDS);
            scheduledThreadPool.shutdown();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
