package com.concurrent.thread;

/**
 * 守护线程
 * Daemon线程是一种守护型线程，因为它主要用作程序中后台调度与支持性工作。
 * 这意味着当一个Java虚拟机中没有一个非守护线程时，Java虚拟机将退出。
 * 在构建守护线程时不能依赖finally块中的操作来确保执行关闭和清理资源的逻辑。
 * User: shijingui
 * Date: 2016/2/5
 */
public class ThreadDaemon {

    public static void main(String... args) {
        Thread thread = new Thread(new DaemonRunner(), "DaemonRunner");
        //在线程启动之前设置
        thread.setDaemon(true);
        thread.start();
    }

    static class DaemonRunner implements Runnable {
        @Override
        public void run() {
            try {
                SleepUtil.second(10);
            } finally {
                System.out.println("DaemonThread finally run.");
            }
        }
    }
}
