package org.banyan.concurrent.thread;

/**
 * join线程间写作
 *
 * @author krisjin
 * @date 2020/11/19
 */
public class ThreadJoin {
    public static void main(String[] args) throws InterruptedException {
//        Thread t1 = new Thread(() -> {
//            System.err.println(Thread.currentThread().getName() + " thread execute end...");
//        });
//
//        Thread t2 = new Thread(() -> {
//            System.err.println(Thread.currentThread().getName() + " thread execute end...");
//        });
//
//        t1.setName("t1");
//        t1.start();
//
//        t2.setName("t2");
//        t2.start();
//
//        t1.join();
//        t2.join();
//
//        System.err.println("main thread execute end...");

        test2();

    }


    public static void test2() {
        Thread t2 = new Thread(() -> {
            System.err.println("t2...");
        });
        Thread t1 = new Thread(() -> {
            try {
                t2.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.err.println("t1...");

        });


        t1.start();
        t2.start();
    }
}
