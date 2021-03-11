package org.banyan.concurrent.sync;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 基础的同步器,JUC提供了很多的具体的同步实现类，这些不同的同步类对应了搁置的应用场景，在并发编程中，特别是大数据开源框架中
 * Barriers are used for blocking a group of threads until they come together at
 * a single point in order to proceed. Basically, convergence of threads.
 * <p>
 * Accepts a runnable in it's constructor to be called when the threads reach the
 * barrier, but before its unblocked
 * <p>
 * Most common implementation is cyclic barrier.
 */
public class UsingBarriers {

    public static void main(String[] args) {
        ExecutorService executor = Executors.newCachedThreadPool();
        Runnable barrierAction = () -> System.out.println("Well done, guys!");
        CyclicBarrier barrier = new CyclicBarrier(11, barrierAction);
        System.out.println(System.getProperty("java.home"));
        Runnable task = () -> {
            try {
                // simulating a task that can take at most 1sec to run
                System.out.println("Doing task for " + Thread.currentThread().getName());
                Thread.sleep(10);
                System.out.println("Done for " + Thread.currentThread().getName());
                barrier.await();
            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }
        };

        for (int i = 0; i < 10; i++) {
            executor.execute(task);
        }
        executor.shutdown();
    }

}
