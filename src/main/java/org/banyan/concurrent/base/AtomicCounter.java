package org.banyan.concurrent.base;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author krisjin on 2018/2/21
 */
public class AtomicCounter {

    public static void main(String[] args) throws InterruptedException {
        AtomicInteger counter = new AtomicInteger();

        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < 10; i++)
            executorService.execute(new Runnable() {
                @Override
                public void run() {

                    System.out.println(Thread.currentThread().getName() + " : " + counter.incrementAndGet());
                }
            });

        executorService.shutdown();
        executorService.awaitTermination(100, TimeUnit.SECONDS);

        System.out.println(counter.get());
    }
}
