package org.concurrent.thread;

import org.concurrent.util.SleepUtil;

import java.util.Date;

/**
 * 线程等待通知
 * User: krisjin
 * Date: 2016/2/5
 */
public class WaitNotify {
    static volatile boolean flag = true;
    static Object obj = new Object();

    public static void main(String... args) {
        Thread wait = new Thread(new Wait());
        wait.setName("等待 - ");
        wait.start();
//        SleepUtil.second(2);

        Thread notify = new Thread(new Notify());
        notify.setName("通知 - ");
        notify.start();
    }


    static class Wait implements Runnable {
        @Override
        public void run() {
            try {
                synchronized (obj) {
                    //当条件不满足时，继续wait,同时释放obj的锁
                    while (flag) {
                        System.out.println(Thread.currentThread().getName() + "  is waiting..... " + new Date());
                        obj.wait();
                    }
                }
                System.out.println(Thread.currentThread().getName() + "  is done.... " + new Date());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

    static class Notify implements Runnable {
        @Override
        public void run() {
            synchronized (obj) {
                //获取obj的锁，然后进行通知，通知时不会释放obj的锁,类似于过早通知
                //只有当前线程释放了obj锁后，Wait才能从wait方法返回
                obj.notifyAll();
                flag = false;
                System.out.println(Thread.currentThread().getName() + " is notify done...." + new Date());
                SleepUtil.second(5);
            }
        }
    }
}
