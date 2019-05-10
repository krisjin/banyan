package org.banyan.concurrent.threadpool;

/**
 * @author krisjin on 2017/5/19
 */
public class PThread extends Thread {

    private ThreadPool threadPool;

    private Runnable target;
    private volatile boolean isShutdown = false;
    private boolean isIdle = false;

    public PThread(Runnable target, String name, ThreadPool threadPool) {
        super(name);
        this.threadPool = threadPool;
        this.target = target;
    }

    public Runnable getTarget() {
        return target;
    }

    public synchronized void setTarget(Runnable target) {
        this.target = target;
        //设置了任务之后，通知run方法，开始执行这个任务
        notifyAll();
    }

    public boolean isShutdown() {
        return isShutdown;
    }

    public boolean isIdle() {
        return isIdle;
    }

    @Override
    public void run() {
        //只要没有关闭，就一直不结束该线程
        while (!isShutdown) {
            isIdle = false;
            if (target != null) {
                target.run();
            }
            //任务结束了到闲置状态
            isIdle = true;
            try {
                //任务结束后不关闭线程，而是放入线程池空闲对列
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

    public synchronized void shutdown() {
        isShutdown = true;
        notifyAll();
    }

}
