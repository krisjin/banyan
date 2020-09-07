package org.banyan.concurrent.thread.waitnotify;

import lombok.SneakyThrows;

/**
 * User:shijingui
 * Date:2019/4/3
 *  
 */
public class EvenOddInWaitNotify {

    static int num = 1;
    static Object lock = new Object();

    public static void main(String[] args) {
        new Thread(new EvenOddInWaitNotify.Odd()).start();
        new Thread(new EvenOddInWaitNotify.Even()).start();
    }

    static class Odd implements Runnable {
        @SneakyThrows
        public void run() {
            synchronized (lock) {
                while (num <= 100) {
                    if (num % 2 != 0) {
                        System.out.println("奇数:" + num);
                        lock.notify();
                        ++num;
                    } else {
                        lock.wait();
                    }
                }
            }
        }
    }

    static class Even implements Runnable {
        @SneakyThrows
        public void run() {
            synchronized (lock) {
                while (num <= 100) {
                    if (num % 2 == 0) {
                        System.out.println("偶数:" + num);
                        lock.notify();
                        ++num;
                    } else {
                        lock.wait();
                    }
                }
            }
        }
    }
}
