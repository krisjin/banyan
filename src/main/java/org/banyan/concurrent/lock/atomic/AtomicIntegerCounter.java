package org.banyan.concurrent.lock.atomic;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

/**
 * Integer原子性操作
 */
public class AtomicIntegerCounter {
    public static int num;

    public static void main(String[] args) {
//        useSyn();
        testIncrement();
    }

    public static void useAtomic() throws InterruptedException {
        AtomicInteger counter = new AtomicInteger();
        ExecutorService executorService = Executors.newCachedThreadPool();

        for (int i = 0; i < 10; i++) {
            executorService.execute(() -> {
                System.out.println(Thread.currentThread().getName() + " : " + counter.incrementAndGet());
            });
        }
        executorService.shutdown();
        executorService.awaitTermination(100, TimeUnit.SECONDS);
    }

    public static void useSyn() {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 6; i++) {
            executorService.execute(() -> {
                synchronized (AtomicInteger.class) {
                    ++num;
                    System.out.println(Thread.currentThread().getName() + ":" + num);
                }
            });
        }
        executorService.shutdown();
    }

    public static void testIncrement() {
        IntStream.range(0, 10).forEach(i -> {
            System.out.println(i);
        });

    }

}
