package com.concurrent.thread;

/**
 * 等待通知
 * User: shijingui
 * Date: 2016/2/5
 */
public class WaitNotify {

    public static void main(String... args) {
        Thread waitThread = new Thread(new Wait(), "WaitThread");
        waitThread.start();
        SleepUtil.second(2);
        Thread notifyThread = new Thread(new Notify(), "NotifyThread");
        notifyThread.start();
    }


    static class Wait implements Runnable {
        @Override
        public void run() {
            try {
                System.out.println("WaitThread is waiting start....");
                synchronized (WaitNotify.class) {
                    WaitNotify.class.wait();
                }
                System.out.println("WaitThread is waiting end....");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

    static class Notify implements Runnable {
        @Override
        public void run() {
            synchronized (WaitNotify.class) {
                WaitNotify.class.notifyAll();
            }
        }
    }
}
