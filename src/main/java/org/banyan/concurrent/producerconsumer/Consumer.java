package org.banyan.concurrent.producerconsumer;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * @author krisjin on 2017/5/17
 */
public class Consumer implements Runnable {
    private BlockingQueue<Object> queue;
    private volatile boolean isRunning = true;

    public Consumer(BlockingQueue<Object> queue) {
        this.queue = queue;
    }

    public void run() {
        Random r = new Random();
        System.out.println("consumer thread is start...");

        try {
            if (isRunning) {
                String msg = (String) queue.poll(10, TimeUnit.MILLISECONDS);
                if (msg != null) {
                    System.out.println("consumer data = " + msg);
                    Thread.sleep(r.nextInt(1000));
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
            Thread.currentThread().interrupt();
        }
    }

    public void stop() {
        isRunning = false;
    }
}
