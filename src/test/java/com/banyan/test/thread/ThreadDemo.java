package com.banyan.test.thread;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * User:krisjin
 * Date:2019/5/28
 *  
 */
public class ThreadDemo {


    public static void main(String[] args) {


        ExecutorsUtil executorsUtil = new ExecutorsUtil(10, 10, 1, TimeUnit.SECONDS, new LinkedBlockingQueue<>(1000), "test");


        executorsUtil.execute(new Runnable() {
            @Override
            public void run() {
                System.err.println("111");
            }
        });

//        executorsUtil.


        Thread t = new Thread(new TaskThread());
        t.start();
    }


    public static class TaskThread implements Runnable {

        @Override
        public void run() {

            try {
                Thread.sleep(20000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("Task Thread ...");

        }
    }
}
