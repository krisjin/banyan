package test;

import org.banyan.concurrent.lock.reentrant.ProducerConsumerQueue;

/**
 * User:krisjin
 * Date:2019/3/4
 *  
 */
public class ProducerConsumerQueueTest {

    public static void main(String[] args) {
        ProducerConsumerQueue producerConsumerQueue = new ProducerConsumerQueue();
        Thread take = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    for (; ; ) {

                        Object s = producerConsumerQueue.take();
                        System.out.println(Thread.currentThread().getName() + " consumer " + s);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        take.start();

        Thread producer = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    for (int i = 0; i < 1000; i++) {
                        String str = "result " + i;
                        producerConsumerQueue.put(str);
                        System.out.println(Thread.currentThread().getName() + " producer " + i);
                    }

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        producer.start();
    }
}
