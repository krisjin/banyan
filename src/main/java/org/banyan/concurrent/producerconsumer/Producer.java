package org.banyan.concurrent.producerconsumer;

import java.util.Date;
import java.util.Random;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author krisjin on 2017/5/17
 */
public class Producer implements Runnable {
    private volatile boolean isRunning = true;
    private BlockingDeque<Object> queue;
    private AtomicInteger count = new AtomicInteger();

    public Producer(BlockingDeque<Object> queue) {
        this.queue = queue;
    }

    public void run() {
        Random random = new Random();
        System.out.println("producer thread is start...");
        try {
            while (isRunning)
                for (int i = 0; i < 100; i++) {
                    Thread.sleep(random.nextInt(20));
                    queue.offer("hi date:" + new Date());
                }
        } catch (InterruptedException e) {
            e.printStackTrace();
            Thread.currentThread().interrupt();
        }
    }

    public void stop() {
        isRunning = false;
        System.out.println(Thread.currentThread().getId() + " thread is stop");
    }
}
