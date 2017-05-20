package com.concurrent.threadpool;

/**
 * @author shijingui on 2017/5/20
 */
public class Test {

    public static void main(String[] args) {
        testThread();
    }


    public static void testThread() {
        long st = System.currentTimeMillis();
        for (int i = 0; i < 1000; i++) {
            new Thread(new MyThread("testNoThreadPool" + i)).start();
        }
        long cost = System.currentTimeMillis() - st;
        System.out.println("cost:" + cost + " ms");
    }


    public static void testThreadPool() {
        long st = System.currentTimeMillis();
        for (int i = 0; i < 1000; i++) {
            ThreadPool.getInstance().start(new MyThread("TestThreadPool" + i));
        }
        long cost = System.currentTimeMillis() - st;
        System.out.println("cost:" + cost + " ms");
    }
}
