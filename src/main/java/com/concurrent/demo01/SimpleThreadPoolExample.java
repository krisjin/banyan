package com.concurrent.demo01;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author krisjin
 * @date 2015-1-27
 */
public class SimpleThreadPoolExample {

    public static void main(String[] args) {

        runTask();

    }

    public static void runTask() {
        long startTime = System.currentTimeMillis();
        ExecutorService executorService = Executors.newFixedThreadPool(100);
        Runnable runnable = new WorkThread();

        for (int i = 0; i < 60000000; i++) {
            executorService.execute(runnable);
        }
        executorService.shutdown();


        while (!executorService.isTerminated()) {
        }
        System.out.println("stopped");
        long endTime = System.currentTimeMillis();

        System.out.println("10000000 counts costs time:" + (endTime - startTime) / 1000 + "s");

    }

}
