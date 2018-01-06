package org.concurrent.thread.join;

/**
 * User: shijingui
 * Date: 2016/9/18
 */
public class ResultThread implements Runnable {
    Thread calculateThread;

    public ResultThread(Thread calculateThread) {
        this.calculateThread = calculateThread;
    }

    @Override
    public void run() {
        try {
            calculateThread.join();
            System.out.println(Thread.currentThread().getName() + " end.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
