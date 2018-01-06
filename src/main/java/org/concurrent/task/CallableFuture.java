package org.concurrent.task;

import java.util.concurrent.*;

/**
 * 使用Callable 和 Future 返回线程执行结果
 * Date: 2015/9/22
 * Time: 10:50
 */
public class CallableFuture {

    public static void main(String[] args) throws InterruptedException {
        //创建一个缓冲线程池
        ExecutorService executorService = Executors.newCachedThreadPool();

        //创建一个任务，实现了Callable接口
        Task task = new Task();

        //执行任务
        Future<Integer> future = executorService.submit(task);

        executorService.shutdown();
        TimeUnit.MILLISECONDS.sleep(100);

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
            System.out.println("任务线程在进行计算");
            int sum = 0;
            for (int i = 0; i < 100; i++) {
                sum += i;
            }
            return sum;
        }
    }
}
