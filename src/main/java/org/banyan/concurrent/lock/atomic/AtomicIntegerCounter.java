package org.banyan.concurrent.lock.atomic;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

/**
 * Atomic Integer原子操作
 */
public class AtomicIntegerCounter {
    public static int num;

    private static AtomicInteger atomicInt = new AtomicInteger(0);

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
        ExecutorService executor = Executors.newFixedThreadPool(2);
        atomicInt.set(0);
        IntStream.range(0, 10).forEach(i -> executor.submit(atomicInt::incrementAndGet));
        System.out.println("increment int: " + atomicInt.get());
        executor.shutdownNow();
    }

}
