package org.concurrent.thread.pool;

/**
 * User: krisjin
 * Date: 2016/2/19
 */
public interface ThreadPool<Job extends Runnable> {

    /**
     * 执行提交的任务
     *
     * @param job
     */
    public void execute(Job job);

    /**
     * 关闭线程池
     */
    public void shutdown();

    /**
     * 增加工作线程
     *
     * @param workersNum
     */
    public void addWorkers(int workersNum);

    /**
     * 减少工作线程
     *
     * @param workNum
     */
    public void removeWorkers(int workNum);

    public int getJobSize();
}
