package com.concurrent.threadpool;

/**
 * @author shijingui on 2017/5/19
 */
public class PThread extends Thread {

    private ThreadPool threadPool;

    private Runnable target;
    private boolean isShutdown = false;
    private boolean isIdle = false;

    public PThread(Runnable target, String name, ThreadPool threadPool) {
        this.threadPool = threadPool;
        this.threadPool = threadPool;
        this.target = target;
    }
}
