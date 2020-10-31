package org.banyan.concurrent.producerconsumer;

import java.util.concurrent.BlockingDeque;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * @author krisjin on 2017/5/17
 */
public class ClientMain {

    public static void main(String[] args) throws InterruptedException {
        BlockingDeque<Object> queue = MessageQueue.get();
        ExecutorService executorService = Executors.newCachedThreadPool();

        Producer producer = new Producer(queue);
        Consumer consumer = new Consumer(queue);

        executorService.execute(producer);
        executorService.execute(consumer);

        Thread.sleep(1000);
        producer.stop();
        consumer.stop();


        executorService.shutdownNow();
    }
}
