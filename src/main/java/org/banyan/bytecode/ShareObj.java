package org.banyan.bytecode;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * User:krisjin
 * Date:2020-05-01
 */
public class ShareObj {
    static class ShareCounter {//共享计数器对象
        public int counter = 0;
    }


    static class Task implements Runnable {
        private ShareCounter shareCounter;

        public Task(ShareCounter shareCounter) {
            this.shareCounter = shareCounter;
        }

        public void run() {
            System.err.println(Thread.currentThread().getName() + ", ++before, " + shareCounter.counter);
            shareCounter.counter++;
            System.err.println(Thread.currentThread().getName() + ", ++after,  " + shareCounter.counter + "\n");
        }
    }

    public static void main(String[] args) {
        ShareCounter shareCounter = new ShareCounter();
        ExecutorService executorService = Executors.newFixedThreadPool(4);

        for (int i = 0; i < 2; i++)
            executorService.submit(new Task(shareCounter));

        try {
            Thread.currentThread().join(500);
            System.err.println(shareCounter.counter);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        executorService.shutdownNow();
    }
}
