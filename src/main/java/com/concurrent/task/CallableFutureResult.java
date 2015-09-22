package com.concurrent.task;

import java.util.concurrent.*;

/**
 * <p/>
 * User : krisibm@163.com
 * Date: 2015/9/22
 * Time: 10:50
 */
public class CallableFutureResult {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        Task task = new Task();

        Future<Integer> future = executorService.submit(task);

        executorService.shutdown();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("主线程在执行任务");

        try {
            Integer result = future.get();
            System.out.println("task execute result is : " + result);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

}

