package com.concurrent.threadpool;

import java.util.concurrent.CountDownLatch;

/**
 * <p/>
 * User : krisibm@163.com
 * Date: 2015/9/14
 * Time: 14:33
 */
public class WorkerThread implements Runnable {

    private CountDownLatch startSignal;

    private CountDownLatch doneSignal;

    public WorkerThread(CountDownLatch startSignal, CountDownLatch doneSignal) {
        this.startSignal = startSignal;
        this.doneSignal = doneSignal;
    }

    @Override
    public void run() {
        try {
            startSignal.await();
            doSomething();
            doneSignal.countDown();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void doSomething() {
        System.out.println(Thread.currentThread().getName());
    }
}
