package org.banyan.concurrent.producerconsumer;

import java.util.Random;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author krisjin on 2017/5/17
 */
public class Producer implements Runnable {
    private volatile boolean isRunning = true;
    private BlockingDeque<String> queue;
    private AtomicInteger count = new AtomicInteger();

    public Producer(BlockingDeque<String> queue) {
        this.queue = queue;
    }


    @Override
    public void run() {
        Random random = new Random();
        System.out.println("producer thread is start...");
        while (isRunning) {
            try {
                Thread.sleep(random.nextInt(1000));
                queue.offer("hihi");

            } catch (InterruptedException e) {
                e.printStackTrace();
                Thread.currentThread().interrupt();
            }
        }

    }

    public void stop() {
        isRunning = false;
        System.out.println(Thread.currentThread().getId() + " thread is stop");
    }
}
