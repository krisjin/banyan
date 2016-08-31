package com.concurrent.thread;

/**
 * User: shijingui
 * Date: 2016/8/25
 */
public class ThreadJoin {


    public static void main(String[] args) {
        Thread threadA = new ThreadA();
        Thread threadB = new ThreadB();

        threadA.start();
//        try {
//            threadA.join();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        threadB.start();


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
