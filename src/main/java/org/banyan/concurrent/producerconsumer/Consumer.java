package org.banyan.concurrent.producerconsumer;

import java.util.Random;
import java.util.concurrent.BlockingQueue;

/**
 * @author krisjin on 2017/5/17
 */
public class Consumer implements Runnable {
    private BlockingQueue<String> queue;

    public Consumer(BlockingQueue<String> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        Random r = new Random();
        System.out.println("consumer thread is start...");
        while (true) {
            try {
                String msg = queue.take();
                if (msg != null) {

                    System.out.println("consumer data =" + msg);
                    Thread.sleep(r.nextInt(1000));
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
                Thread.currentThread().interrupt();
            }
        }

    }
}
