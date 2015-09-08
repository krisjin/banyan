package com.concurrent.objectshare;

/**
 * 存在可变的共享变量，飞线程安全类。
 * <p/>
 * User : krisibm@163.com
 * Date: 2015/9/8
 * Time: 21:14
 */
public class NoVisibility {

    private static boolean ready;
    private static int number;


    public static void main(String[] args) {
        new ReaderThread().start();
        new ReaderThread().start();
        ready = true;
        number = 31;

    }

    private static class ReaderThread extends Thread {
        @Override
        public void run() {
            while (!ready) {
                Thread.yield();
            }

            System.out.println("read number : " + number);
        }
    }


}
