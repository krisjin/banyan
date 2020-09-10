package org.banyan.concurrent.thread;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * User:krisjin
 * Date:2019/3/7
 *  
 */
public class CountThread {


    public static void main(String[] args) {
        AtomicInteger num = new AtomicInteger(1);
        Object lock = new Object();

        Thread print1 = new Thread(new Printer1(num, lock));
        print1.setName("print1 - ");

        Thread print2 = new Thread(new Printer2(num, lock));
        print2.setName("print2 - ");

        print2.start();
        print1.start();

    }

    static class Printer1 implements Runnable {
        volatile AtomicInteger num;
        Object lock;

        public Printer1(AtomicInteger num, Object lock) {
            this.num = num;
            this.lock = lock;
        }

        @Override
        public void run() {
            while (num.get() <= 100) {
                synchronized (lock) {
                    if ((num.get() % 2) != 0) {
                        System.err.println(Thread.currentThread().getName() + num.get());
                        num.incrementAndGet();
                        lock.notify();

                    } else {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }

            }

        }
    }

    static class Printer2 implements Runnable {
        AtomicInteger num;
        Object lock;

        public Printer2(AtomicInteger num, Object lock) {
            this.num = num;
            this.lock = lock;
        }

        @Override
        public void run() {
            while (num.get() <= 100) {
                synchronized (lock) {
                    if (num.get() % 2 == 0) {
                        System.err.println(Thread.currentThread().getName() + num.get());
                        num.incrementAndGet();
                        lock.notify();
                    } else {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }
}
