package com.concurrent.thread;

/**
 * 等待通知
 * User: shijingui
 * Date: 2016/2/5
 */
public class WaitNotify {

    static volatile boolean flag = true;
    static Object obj = new Object();

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
                while (flag) {
                    synchronized (obj) {
                        System.out.println(Thread.currentThread().getName() + "  is waiting..... ");
                        obj.wait();
                    }
                }
                System.out.println(Thread.currentThread().getName() + "  is done.... ");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

    static class Notify implements Runnable {
        @Override
        public void run() {
            synchronized (obj) {
                //获取obj的锁，然后进行通知，通知时不会释放obj的锁
                obj.notifyAll();
                System.out.println(Thread.currentThread().getName() + " is notify done....");
//                SleepUtil.second(1);
                flag = false;
            }
        }
    }
}
