package com.concurrent.threadpool;

/**
 * @author shijingui on 2017/5/19
 */
public class PThread extends Thread {

    private ThreadPool threadPool;

    private Runnable target;
    private volatile boolean isShutdown = false;
    private boolean isIdle = false;

    public PThread(Runnable target, String name, ThreadPool threadPool) {
        super();
        this.threadPool = threadPool;
        this.target = target;
    }

    public Runnable getTarget() {
        return target;
    }

    public boolean isShutdown() {
        return isShutdown;
    }


    public boolean isIdle() {
        return isIdle;
    }

    @Override
    public void run() {

        while (!isShutdown) {
            isIdle = false;
            if (target != null) {
                target.run();
            }
            isIdle = true;


            try {
                threadPool.add(this);
                synchronized (this) {
                    wait();//thread is idle ,wait new thread
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            isIdle = false;
        }
    }


    public synchronized void setTarget(Runnable target) {
        this.target = target;
        notifyAll();// set new task,notify run method,start execute this task
    }

    public synchronized void shutdown() {
        isShutdown = true;
        notifyAll();
    }
}
