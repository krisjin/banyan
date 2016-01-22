package com.concurrent.thread;

import java.util.Stack;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * <p/>
 * User : krisibm@163.com
 * Date: 2015/4/11
 * Time: 12:00
 */
public class ProducerConsumer {
    static Stack<String> message = new Stack<String>();

    final static int MAX_SIZE = 10;

    public static void main(String[] args) {

        Thread t1 = new Thread(new Producer());

        Thread t2 = new Thread(new Consumer(), "Consume-2");
        Thread t3 = new Thread(new Consumer(), "Consume-3");
        Thread t4 = new Thread(new Consumer(), "Consume-4");

        t1.start();
        try {
            t1.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        t2.start();
        t3.start();
        t4.start();


        try {
            t2.join();
            t3.join();
            t4.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }


    static class Producer implements Runnable {
        public void produce(String msg) {

            System.out.println("Producing " + msg);
            message.push(msg);
        }

        public void run() {
            int i = 0;

            while (i++ < MAX_SIZE) {
                synchronized (message) {
                    produce("message" + i);
                    message.notifyAll();
                    try {
                        TimeUnit.MILLISECONDS.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

    }

    static class Consumer implements Runnable {
        AtomicInteger counter = new AtomicInteger();

        @Override
        public void run() {
            while (!end()) {
                synchronized (message) {
                    while (message.isEmpty() && (!end())) {

                        try {
                            message.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    consumer();
                }

            }
        }

        /**
         * @return
         */
        private boolean end() {
            return counter.get() >= MAX_SIZE;
        }

        private void consumer() {
            if (!message.isEmpty()) {
                System.out.println("Consume" + Thread.currentThread().getName() + message.pop());
                counter.incrementAndGet();
            }
        }
    }
}