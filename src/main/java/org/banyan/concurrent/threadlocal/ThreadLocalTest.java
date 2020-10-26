package org.banyan.concurrent.threadlocal;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @User krisjin
 * @Date 2020/9/16
 */
public class ThreadLocalTest {


    static class Task implements Runnable {
        protected ThreadLocal<String> threadLocal = new ThreadLocal<>();
        ConcurrentHashMap aa= new ConcurrentHashMap();

        public void run() {
            aa.put(null,null);
            System.out.println("start :" + threadLocal.get());
            threadLocal.set(Thread.currentThread().getName());
            try {
                t();
            } finally {
//                threadLocal.remove();
            }

        }

        public void t() {
            System.err.println("test : " + threadLocal.get());
        }
    }


    public static void main(String[] args) {
        ExecutorService es = Executors.newFixedThreadPool(5);

        for (int i = 0; i < 20; i++) {
            es.submit(new Task());
        }

        es.shutdown();
        try {
            es.awaitTermination(10L, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
