package org.banyan.concurrent.thread;

/**
 * User: krisjin
 * Date: 2016/8/25
 */
public class ThreadYield {
    public static void main(String[] args) {
        Thread producer = new Producer();
        Thread consumer = new Consumer();

        producer.setPriority(Thread.MIN_PRIORITY); //Min Priority
        consumer.setPriority(Thread.MAX_PRIORITY); //Max Priority

        producer.start();
        consumer.start();
    }

    static class Producer extends Thread {
        @Override
        public void run() {
            for (int i = 1; i <= 5; i++) {
                System.out.println("Producer : Produced Item " + i);
                Thread.yield();
            }
        }
    }

    static class Consumer extends Thread {
        public void run() {
            for (int i = 1; i <= 5; i++) {
                System.out.println("Consumer : Consumed Item " + i);
                Thread.yield();
            }
        }
    }

}
