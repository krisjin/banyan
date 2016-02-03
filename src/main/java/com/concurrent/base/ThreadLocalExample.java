package com.concurrent.base;

/**
 * User: shijingui
 * Date: 2016/2/1
 */
public class ThreadLocalExample {

    static class MyRunnable implements Runnable {
        ThreadLocal<Integer> threadLocal = new ThreadLocal<Integer>();

        @Override
        public void run() {
            threadLocal.set((int) Math.random() * 100);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }


    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(new MyRunnable());
        Thread t2 = new Thread(new MyRunnable());

        t1.start();
        t2.start();
        t1.join();
        t2.join();
    }
}
