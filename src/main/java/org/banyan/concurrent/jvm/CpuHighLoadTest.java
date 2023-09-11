package org.banyan.concurrent.jvm;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * arthas
 */
public class CpuHighLoadTest {

    private static ExecutorService executorService = Executors.newFixedThreadPool(5);
    public static Object lock = new Object();

    public static void main(String[] args) {
        Task task = new Task();
        executorService.execute(task);
    }

    static class Task implements Runnable {
        long sum = 0L;

        public void run() {
            synchronized (lock) {
                while (true) {
                    sum += 1;
                }
            }
        }
    }

}
