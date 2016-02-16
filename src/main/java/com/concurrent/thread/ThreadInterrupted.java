package com.concurrent.thread;

import com.concurrent.util.SleepUtil;

/**
 * 中断可以理解为线程的一个表示位属性
 * User: shijingui
 * Date: 2016/2/5
 */
public class ThreadInterrupted {

    public static void main(String... args) throws Exception {
        Thread sleepRunner = new Thread(new SleepRunner(), "SleepThread");
        sleepRunner.setDaemon(true);
        Thread busyRunner = new Thread(new BusyRunner(), "BusyThread");
        busyRunner.setDaemon(true);
        sleepRunner.start();
        busyRunner.start();
        //休眠5秒，让sleepRunner和busyRunner充分运行
        SleepUtil.second(5);

        sleepRunner.interrupt();
        busyRunner.interrupt();
        System.out.println("SleepThread interrupt is:" + sleepRunner.isInterrupted());
        System.out.println("BusyThread interrupt is:" + busyRunner.isInterrupted());
        SleepUtil.second(2);
    }

    static class SleepRunner implements Runnable {
        @Override
        public void run() {
            while (true)
                SleepUtil.second(10);
        }
    }

    static class BusyRunner implements Runnable {
        @Override
        public void run() {
            while (true) ;
        }
    }
}
