package org.concurrent.thread;

/**
 * User: shijingui
 * Date: 2016/8/25
 */
public class ThreadJoin {

    public static void main(String[] args) {
        Thread threadA = new ThreadA();
        Thread threadB = new ThreadB();

        try {
            threadA.start();
            threadA.join();
            threadB.start();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }

    static class ThreadA extends Thread {
        public void run() {
            System.out.println("ThreadA execute done...");
        }
    }

    static class ThreadB extends Thread {
        public void run() {
            System.out.println("ThreadB execute done...");
        }
    }
}
