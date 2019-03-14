package org.banyan.concurrent.producerconsumer;

import java.util.concurrent.BlockingDeque;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * @author krisjin on 2017/5/17
 */
public class Test {

    public static void main(String[] args) throws InterruptedException {
        BlockingDeque<String> queue = new LinkedBlockingDeque<>();

        Producer p1 = new Producer(queue);
        Producer p2 = new Producer(queue);

        Consumer c1 = new Consumer(queue);
        Consumer c2 = new Consumer(queue);

        ExecutorService executorService = Executors.newCachedThreadPool();

        executorService.execute(p1);
        executorService.execute(p2);
        executorService.execute(c1);
        executorService.execute(c2);

        Thread.sleep(1000);

        p1.stop();
        p2.stop();

        executorService.shutdownNow();

    }
}
