package com.concurrent.task;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

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

