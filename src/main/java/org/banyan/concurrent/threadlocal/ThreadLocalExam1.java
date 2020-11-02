package org.banyan.concurrent.threadlocal;

/**
 * User: krisjin
 * Date: 2016/2/1
 */
public class ThreadLocalExam1 {
    ThreadLocal<String> pinThreadLocal = new ThreadLocal<>();

    public void setPin(String pin) {
        pinThreadLocal.set(pin + ": " + Thread.currentThread().getId() + ": " + Thread.currentThread().getName());
    }

    public String getPin() {
        return pinThreadLocal.get();
    }

    public static void main(String[] args) throws InterruptedException {
        ThreadLocalExam1 threadLocalExam1 = new ThreadLocalExam1();
        threadLocalExam1.setPin("2020");
        System.out.println(threadLocalExam1.getPin());

        Thread t1 = new Thread(new MyRunnable(threadLocalExam1));
        t1.start();
        t1.join();
        System.out.println(threadLocalExam1.getPin());
    }

    static class MyRunnable implements Runnable {
        ThreadLocalExam1 threadLocalExam1;

        public MyRunnable(ThreadLocalExam1 threadLocalExam1) {
            this.threadLocalExam1 = threadLocalExam1;
        }

        public void run() {
            threadLocalExam1.setPin("2021");
            System.out.println(threadLocalExam1.getPin());
        }
    }
}
