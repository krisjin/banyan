package com.concurrent.task;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

/**
 * <p/>
 * User : krisibm@163.com
 * Date: 2015/9/22
 * Time: 11:03
 */
public class CallableFutureTaskResult {

    public static void main(String[] args) {
        ExecutorService executor = Executors.newCachedThreadPool();

        Task task = new Task();
        FutureTask<Integer> futureTask = new FutureTask<Integer>(task);
        executor.submit(futureTask);
        executor.shutdown();

        //主线程休眠1秒钟，CPU执行让给其它线程.
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("主线程在执行任务");

        try {
            Integer result = futureTask.get();
            System.out.println("task execute result is : " + result);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }


    }
}
