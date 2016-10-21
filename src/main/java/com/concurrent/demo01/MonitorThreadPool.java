package com.concurrent.demo01;

import java.util.concurrent.*;

/**
 * @author krisjin
 * @date 2015-1-27
 */
public class MonitorThreadPool {

    public static void main(String[] args) throws InterruptedException {

        RejectedExecutionHandlerImpl rejected = new RejectedExecutionHandlerImpl();

        ThreadFactory tf = Executors.defaultThreadFactory();

        ThreadPoolExecutor executorPool = new ThreadPoolExecutor(300, 300, 3, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>(), tf, rejected);

        MonitorThread monitor = new MonitorThread(executorPool, 3);

        Thread monitorThread = new Thread(monitor);

        monitorThread.start();

        for (int i = 0; i < 10000000; i++) {

            executorPool.execute(new WorkThread());

        }

        Thread.sleep(5000);
        executorPool.shutdown();

        while (!executorPool.isTerminated()) {
        }

        Thread.sleep(3000);
        monitor.shutdown();
    }

}
