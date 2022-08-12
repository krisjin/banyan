package org.banyan.concurrent.future;

import org.banyan.concurrent.util.SleepUtil;

import java.util.concurrent.*;

/**
 * Future模式的核心在于去除了主函数的等待时间，利用原来等待的时间处理其它逻辑
 * User krisjin
 * Date 2017/5/10
 */
public class FutureTaskDemo {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        FutureTask<String> future = new FutureTask<String>(new StrTask("Hello"));

        ExecutorService executorService = Executors.newFixedThreadPool(1);
        executorService.submit(future);
        System.err.println("任务已提交");

        System.out.println(future.get());
        executorService.shutdown();
    }

    static class StrTask implements Callable<String> {
        private String str;

        public StrTask(String str) {
            this.str = str;
        }

        @Override
        public String call() throws Exception {
            SleepUtil.second(3);
            return "hi," + this.str;
        }
    }

}
