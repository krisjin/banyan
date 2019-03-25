package org.banyan.concurrent.thread;

import org.banyan.concurrent.util.SleepUtil;

import java.util.Date;

/**
 * 线程等待通知
 * <p>
 * User: krisjin
 * Date: 2016/2/5
 */
public class WaitNotify {
    static volatile boolean flag = true;
    static Object lock = new Object();

    public static void main(String... args) {
        Thread wait = new Thread(new Wait());
        wait.setName("等待 - ");
        wait.start();

        Thread notify = new Thread(new Notify());
        notify.setName("通知 - ");
        notify.start();
    }

    /**
     * wait()方法可以使调用该方法的线程释放共享资源的锁，然后从运行状态退出，进入等待队列，直到再次被唤醒。
     */
    static class Wait implements Runnable {
        @Override
        public void run() {
            try {
                synchronized (lock) {
                    //当条件不满足时，继续wait,同时释放obj的锁
//                    while (flag) {
                    System.out.println(Thread.currentThread().getName() + "  is waiting..... " + new Date());
                    lock.wait();
//                    }
                }
                System.out.println(Thread.currentThread().getName() + "  is done.... " + new Date());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

    /**
     * notifyAll()方法可以使所有正在等待队列中等待同一共享资源的"全部"线程，从阻塞状态进入可运行状态。
     * 获取obj的锁，然后进行通知，通知时不会立即释放obj的锁,类似于过早通知,
     * 只有当前线程释放了obj锁后，Wait才能从wait方法返回
     */
    static class Notify implements Runnable {
        @Override
        public void run() {
            synchronized (lock) {
                lock.notifyAll();
//                flag = false;
                System.out.println(Thread.currentThread().getName() + " is notify done...." + new Date());
                SleepUtil.second(5);
            }
        }
    }
}
