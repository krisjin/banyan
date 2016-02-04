package com.concurrent.task;

import java.util.concurrent.*;

/**
 * Date: 2015/9/22
 * Time: 11:03
 */
public class CallableFutureTask {

    public static void main(String[] args) throws InterruptedException {
        Task task = new Task();
        FutureTask<Integer> futureTask = new FutureTask<Integer>(task);

        //第一种执行方式
//        ExecutorService executor = Executors.newFixedThreadPool(2);
//        executor.submit(futureTask);
//        executor.shutdown();

        //第二种执行方式
        Thread thread = new Thread(futureTask);
        thread.start();

        //主线程休眠1秒钟，CPU执行让给其它线程.
        TimeUnit.MILLISECONDS.sleep(1000);
        System.out.println("主线程在执行任务");
        try {
            futureTask.cancel(true);
            System.out.println(futureTask.isCancelled());
            Integer result = futureTask.get();
            System.out.println("task execute result is : " + result);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (CancellationException cancellationException) {
            cancellationException.printStackTrace();
        }
    }

    static class Task implements Callable<Integer> {
        @Override
        public Integer call() throws Exception {
            //让任务线程睡眠，以便取消当前线程，如果线程已执行完成，取消线程的工作将没有效果。
            TimeUnit.MILLISECONDS.sleep(1000);
            System.out.println("任务线程在进行计算");
            int sum = 0;
            for (int i = 0; i < 100; i++) {
                sum += i;
            }
            return sum;
        }
    }
}
