package org.banyan.concurrent.threadpool;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author krisjin on 2017/5/19
 */
public class ThreadPool {

    private static ThreadPool instance = null;
    //空闲线程队列
    private List<PThread> idleThreads;

    //线程总数
    private AtomicInteger threadCount = new AtomicInteger(0);

    private boolean isShutdown = false;

    public ThreadPool() {
        this.idleThreads = Collections.synchronizedList(new ArrayList<PThread>());
    }

    //获取线程池实例
    public synchronized static ThreadPool getInstance() {
        if (instance == null) {
            instance = new ThreadPool();
        }
        return instance;
    }

    public Integer getSize() {
        return threadCount.get();
    }

    /**
     * 增加线程入线程池
     *
     * @param pThread
     */
    protected synchronized void add(PThread pThread) {
        if (!isShutdown) {
            idleThreads.add(pThread);
        } else {
            pThread.shutdown();
        }
    }


    public synchronized void shutdown() {
        isShutdown = true;
        for (int i = 0; i < idleThreads.size(); i++) {
            PThread idleThread = (PThread) idleThreads.get(i);
            idleThread.shutdown();
        }
    }


    public synchronized void start(Runnable runnable) {
        PThread thread = null;
        //如果有空线程则直接使用
        if (idleThreads.size() > 0) {
            int lastIndex = idleThreads.size() - 1;
            thread = idleThreads.get(lastIndex);

            idleThreads.remove(lastIndex);
            thread.setTarget(runnable);
            //没有空闲线程则直接创建
        } else {
            threadCount.incrementAndGet();
            thread = new PThread(runnable, "Thread #" + threadCount.get(), this);
            //启动线程
            thread.start();
        }
    }
}
