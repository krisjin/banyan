package org.banyan.features.synchronizers;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Barriers are used for blocking a group of threads until they come together at
 * a single point in order to proceed. Basically, convergence of threads.
 * <p>
 * Accepts a runnable in it's constructor to be called when the threads reach the
 * barrier, but before its unblocked
 * <p>
 * Most common implementation is cyclic barrier.
 * <p>
 * 内存屏障或内存栅栏，也就是让一个CPU处理单元中的内存状态对其它处理单元可见的一项技术。
 */
public class UsingBarriers {

    public static void main(String[] args) {

        ExecutorService executor = Executors.newCachedThreadPool();
        Runnable barrierAction = () -> System.out.println("Well done, guys!");

        CyclicBarrier barrier = new CyclicBarrier(10, barrierAction);

        Runnable task = () -> {
            try {
                // simulating a task that can take at most 1sec to run
                System.out.println("Doing task for " + Thread.currentThread().getName());
                Thread.sleep(new Random().nextInt(10) * 100);
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
