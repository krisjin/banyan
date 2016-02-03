package com.concurrent.task;

import java.util.concurrent.*;

/**
 * Date: 2015/9/22
 * Time: 10:50
 */
public class CallableFutureResult {

    public static void main(String[] args) {
        //创建一个缓冲线程池
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

    static class Task implements Callable<Integer> {
        @Override
        public Integer call() {
            System.out.println("子线程在进行计算");
            int sum = 0;
            for (int i = 0; i < 100; i++) {
                sum += i;
            }
            return sum;
        }
    }
}
