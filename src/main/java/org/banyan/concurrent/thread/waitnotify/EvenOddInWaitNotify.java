package org.banyan.concurrent.thread.waitnotify;

import lombok.SneakyThrows;

/**
 * User:krisjin
 * Date:2019/4/3
 */
public class EvenOddInWaitNotify {
    static int num = 1;
    static Object lock = new Object();

    public static void main(String[] args) {
        new Thread(new EvenOddInWaitNotify.Even()).start();
        new Thread(new EvenOddInWaitNotify.Odd()).start();
    }

    static class Odd implements Runnable {
        @SneakyThrows
        public void run() {
            synchronized (lock) {
                while (num <= 100) {
                    if (num % 2 != 0) {
                        System.out.println("奇数:" + num);
                        ++num;
                        lock.notify();
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
                        ++num;
                        lock.notify();
                    } else {
                        lock.wait();
                    }
                }
            }
        }
    }
}
