package org.banyan.concurrent.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 线程优先级:
 * 操作系统采用十分的方式调度运行的线程，操作系统会分出一个个时间片，线程会分配到若干时间片，当线程的时间片用完了就会发生线程调度。
 * 并等待下次分配。线程分配到的时间片多少也决定了线程使用处理器资源的多少。而线程优先级就是决定线程需要多或者少分配一些处理器资源的线程属性。
 * 设置线程优先级时，针对频繁阻塞（休眠或者IO操作）的线程需要设置较高的优先级，而偏重计算（需要较多CPU时间或者偏运算）的线程则设置较低的优先级
 * 确保处理器不会被独占
 * User: krisjin
 * Date: 2016/2/4
 */
public class ThreadPriority {

    private static volatile boolean notStart = true;
    private static volatile boolean notEnd = true;

    public static void main(String[] args) throws InterruptedException {
        List<Job> jobList = new ArrayList<Job>();
        for (int i = 0; i < 10; i++) {
            int priority = i < 5 ? Thread.MIN_PRIORITY : Thread.MAX_PRIORITY;
            Job job = new Job(priority);
            jobList.add(job);
            Thread thread = new Thread(job, "Thread:" + i);
            thread.setPriority(priority);
            thread.start();
        }
        notStart = false;
        TimeUnit.MILLISECONDS.sleep(10);
        notEnd = false;

        for (Job job : jobList) {
            System.out.println("Job priority:" + job.priority + ", count:" + job.jobCount);
        }
    }

    static class Job implements Runnable {
        private int priority;
        private long jobCount;

        public Job(int priority) {
            this.priority = priority;
        }

        @Override
        public void run() {
            while (notStart) Thread.yield();

            while (notEnd) {
                Thread.yield();
                jobCount++;
            }
        }
    }
}
