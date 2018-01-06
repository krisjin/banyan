package org.concurrent.future.internal;

import org.concurrent.util.SleepUtil;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

/**
 * Future模式的核心在于去除了主函数的等待时间，利用原来等待的时间处理其它逻辑
 * User shijingui
 * Date 2017/5/10
 */
public class Test {
    public static void main(String[] args) throws ExecutionException, InterruptedException {

        FutureTask<String> future = new FutureTask<String>(new RealData("Hello"));

        ExecutorService executorService = Executors.newFixedThreadPool(1);
        //开启线程执行任务，执行RealData中的call方法
        executorService.submit(future);
        System.out.println("任务执行完成");

        SleepUtil.second(2);
        System.out.println("其它业务逻辑执行完成...");
        System.out.println(future.get());
        executorService.shutdown();
    }

}
