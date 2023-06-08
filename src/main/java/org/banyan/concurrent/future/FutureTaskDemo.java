package org.banyan.concurrent.future;

import java.util.concurrent.*;

/**
 * Future模式的核心在于去除了主函数的等待时间，利用原来等待的时间处理其它逻辑
 */
public class FutureTaskDemo {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask<String> future = new FutureTask<String>(new StrTask("Hello"));
        ExecutorService executorService = Executors.newFixedThreadPool(1);
        executorService.submit(future);
        System.out.println("任务已提交");
        System.out.println(getName());
        System.out.println(future.get());

        executorService.shutdown();
    }

    static class StrTask implements Callable<String> {
        private String str;

        public StrTask(String str) {
            this.str = str;
        }

        @Override
        public String call() throws InterruptedException {
            TimeUnit.SECONDS.sleep(5);
            return this.str + " world!";
        }
    }

    static String getName() throws InterruptedException {
        TimeUnit.SECONDS.sleep(2);
        return "kris";
    }

}
