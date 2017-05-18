package com.concurrent.threadpool;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author shijingui on 2017/5/19
 */
public class ThreadPool {

    private static ThreadPool instance = null;

    private List<PThread> idleThreads;

    private AtomicInteger threadCount = new AtomicInteger(0);

    private boolean isShutdown;

    public ThreadPool() {
        this.idleThreads = Collections.synchronizedList(new ArrayList<PThread>());

    }

    public Integer getSize() {
        return threadCount.get();
    }

    public synchronized static ThreadPool getInstance() {
        if (instance == null) {
            instance = new ThreadPool();
        }
        return instance;
    }


    protected synchronized void add(PThread pThread){
        if (!isShutdown){
            idleThreads.add(pThread);
        }else{
//            pThread.shut();
        }
    }

}
