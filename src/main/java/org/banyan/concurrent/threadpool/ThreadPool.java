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

    private List<PThread> idleThreads;

    private AtomicInteger threadCount = new AtomicInteger(0);

    private boolean isShutdown;

    public ThreadPool() {
        this.idleThreads = Collections.synchronizedList(new ArrayList<PThread>());

    }

    public synchronized static ThreadPool getInstance() {
        if (instance == null) {
            instance = new ThreadPool();
        }
        return instance;
    }

    public Integer getSize() {
        return threadCount.get();
    }

    protected synchronized void add(PThread pThread) {
        if (!isShutdown) {
            idleThreads.add(pThread);
        } else {
//            pThread.shut();
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
        if (idleThreads.size() > 0) {
            int lastIndex = idleThreads.size() - 1;
            thread = idleThreads.get(lastIndex);

            idleThreads.remove(lastIndex);
            thread.setTarget(runnable);
        } else {
            threadCount.incrementAndGet();
            thread = new PThread(runnable, "Thread #" + threadCount.get(), this);
            thread.start();
        }
    }
}
