package com.concurrent.thread.pool;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * 默认线程池
 * User: shijingui
 * Date: 2016/2/19
 */
public class DefaultThreadPool<Job extends Runnable> implements ThreadPool<Job> {

    /**
     * 最大工作线程大小
     */
    private static final int MAX_WORKERS_NUMBER = 10;

    /**
     * 最小工作线程大小
     */
    private static final int MIN_WORKERS_NUMBER = 1;

    /**
     * 默认工作线程大小
     */
    private static final int DEFAULT_WORKERS_NUMBER = 5;

    /**
     * Job任务列表
     */
    private final LinkedList<Job> jobs = new LinkedList<Job>();

    /**
     * 工作线程列表
     */
    private List<Worker> workerList = Collections.synchronizedList(new ArrayList<Worker>());

    private int worker = DEFAULT_WORKERS_NUMBER;

    public DefaultThreadPool() {
        initializeWorker(DEFAULT_WORKERS_NUMBER);
    }

    public DefaultThreadPool(int threadNum) {
        worker = threadNum > MAX_WORKERS_NUMBER ? MAX_WORKERS_NUMBER : threadNum < MIN_WORKERS_NUMBER ? MIN_WORKERS_NUMBER : threadNum;
        initializeWorker(worker);
    }


    @Override
    public void execute(Job job) {
        if (job != null) {
            synchronized (jobs) {
                //添加一个工作任务，然后进行通知
                jobs.add(job);
                jobs.notify();
            }
        }
    }

    @Override
    public void shutdown() {

    }

    @Override
    public void addWorkers(int workersNum) {

    }

    @Override
    public void removeWorkers(int workNum) {

    }

    @Override
    public int getJobSize() {
        return 0;
    }

    public void initializeWorker(int num) {
        for (int i = 0; i < num; i++) {
            Worker worker = new Worker();
            workerList.add(worker);
            Thread thread = new Thread(worker);
            thread.start();
        }
    }

    class Worker implements Runnable {
        @Override
        public void run() {

        }
    }
}
