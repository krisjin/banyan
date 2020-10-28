package org.banyan.concurrent.base;

/**
 * User: krisjin
 * Date: 2016/2/1
 */
public class ThreadLocalExample {
    ThreadLocal<String> pinThreadLocal = new ThreadLocal<>();

    public void setPin(String pin) {
        pinThreadLocal.set(pin + ": " + Thread.currentThread().getId() + ": " + Thread.currentThread().getName());
    }

    public String getPin() {
        return pinThreadLocal.get();
    }

    public static void main(String[] args) throws InterruptedException {
        ThreadLocalExample threadLocalExample = new ThreadLocalExample();
        threadLocalExample.setPin("2020");
        System.out.println(threadLocalExample.getPin());

        Thread t1 = new Thread(new MyRunnable(threadLocalExample));
        t1.start();
        t1.join();
        System.out.println(threadLocalExample.getPin());
    }

    static class MyRunnable implements Runnable {
        ThreadLocalExample threadLocalExample;

        public MyRunnable(ThreadLocalExample threadLocalExample) {
            this.threadLocalExample = threadLocalExample;
        }

        public void run() {
            threadLocalExample.setPin("2021");
            System.out.println(threadLocalExample.getPin());
        }
    }
}
