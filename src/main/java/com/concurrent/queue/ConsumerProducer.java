package com.concurrent.queue;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * User: shijingui
 * Date: 2016/1/19
 */
public class ConsumerProducer {

    //消费者
    static class Consumer implements Runnable {
        private BlockingQueue blockingQueue;

        public Consumer(BlockingQueue queue) {
            this.blockingQueue = queue;
        }

        @Override
        public void run() {
            while (true) {
                try {
                    //阻塞式消费
                    Object val = blockingQueue.take();
                    System.out.println("消费信息：" + val);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    //生产者
    static class Producer implements Runnable {
        private final BlockingQueue blockingQueue;

        public Producer(BlockingQueue queue) {
            this.blockingQueue = queue;
        }

        @Override
        public void run() {
            for (int i = 0; i < 100; i++) {
                try {
                    //阻塞是生产消息
                    blockingQueue.put(i + ":message");
                    System.out.println("生产消息：" + i + ":message");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    public static void main(String[] args) {
        BlockingQueue queue = new LinkedBlockingDeque();
        Consumer consumer = new Consumer(queue);
        Producer producer = new Producer(queue);
        new Thread(producer).start();
        new Thread(consumer).start();

    }
}
