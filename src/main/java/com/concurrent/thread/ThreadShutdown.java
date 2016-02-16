package com.concurrent.thread;

import com.concurrent.util.SleepUtil;

/**
 * 安全的终结线程
 * User: shijingui
 * Date: 2016/2/5
 */
public class ThreadShutdown {

    public static void main(String... args) {
        Thread countThread = new Thread(new Runner(), "CountThread");
        countThread.start();
        //休眠1秒，main线程对CountThread进行中断，使CountThread能够感知中断而结束
        SleepUtil.second(1);
        countThread.interrupt();
        Runner runner = new Runner();

        countThread = new Thread(runner, "CountThread");
        countThread.start();
        //休眠1秒
        SleepUtil.second(1);
        runner.cancel();
    }

    static class Runner implements Runnable {
        private long i;
        private volatile boolean on = true;

        @Override
        public void run() {
            while (on && !Thread.currentThread().isInterrupted()) {
                i++;
            }
            System.out.println(i);
        }

        public void cancel() {
            on = false;
        }
    }
}
