package org.concurrent.base;

import java.util.Date;

/**
 * User: krisjin
 * Date: 2016/2/1
 */
public class ThreadLocalExample {

    static volatile int count = 0;
//    static AtomicInteger count = new AtomicInteger();

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(new MyRunnable(), "Thread-01");
        Thread t2 = new Thread(new MyRunnable(), "Thread-02");

        t1.start();
        t1.join();
        t2.start();
        t2.join();
    }

    static class MyRunnable implements Runnable {
        ThreadLocal<Integer> threadLocal = new ThreadLocal<Integer>();

        @Override
        public void run() {
            threadLocal.set((int) (Math.random() * 10));
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " value is " + threadLocal.get() + " , count is " + ++count + " ,date is " + new Date());

        }
    }
}
