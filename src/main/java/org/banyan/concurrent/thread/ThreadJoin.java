package org.banyan.concurrent.thread;

/**
 * @author krisjin
 * @date 2020/11/19
 */
public class ThreadJoin {
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.err.println(Thread.currentThread().getName() + " thread execute end...");
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.err.println(Thread.currentThread().getName() + " thread execute end...");
            }
        });

        t1.setName("t1");
        t1.start();

        t2.setName("t2");
        t2.start();

        t1.join();
        t2.join();

        System.err.println("main thread execute end...");
    }
}
