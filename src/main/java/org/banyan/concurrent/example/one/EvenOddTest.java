package org.banyan.concurrent.example.one;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * User:shijingui
 * Date:2019/4/3
 *  
 */
public class EvenOddTest {



    static Object lock = new Object();

    public static void main(String[] args) {

        AtomicInteger num = new AtomicInteger(1);
        new Thread(new EvenOddTest.Odd(num)).start();
        new Thread(new EvenOddTest.Even(num)).start();

    }


    static class Odd implements Runnable {
        AtomicInteger num;

        public Odd(AtomicInteger num) {
            this.num = num;
        }

        @Override
        public void run() {

            synchronized (lock) {
                while (num.get() <= 100) {
                    if (num.get() % 2 != 0) {
                        System.out.println("奇数:" + num.get());
                        lock.notify();
                        num.incrementAndGet();
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


    static class Even implements Runnable {
        AtomicInteger num;

        public Even(AtomicInteger num) {
            this.num = num;
        }

        @Override
        public void run() {

            synchronized (lock) {
                while (num.get() <= 100) {
                    if (num.get() % 2 == 0) {
                        System.out.println("偶数:" + num.get());
                        lock.notify();
                        num.incrementAndGet();
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
