package org.banyan.concurrent.example.one;

/**
 * User:shijingui
 * Date:2019/4/3
 *  
 */
public class EvenOddTest {

    static int num = 1;

    static Object lock = new Object();

    public static void main(String[] args) {
        new Thread(new EvenOddTest.Odd()).start();
        new Thread(new EvenOddTest.Even()).start();
    }


    static class Odd implements Runnable {
        @Override
        public void run() {

            synchronized (lock) {
                while (num <= 100) {
                    if (num % 2 != 0) {
                        System.out.println("奇数:" + num);
                        lock.notify();
                        ++num;
                    } else {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }


    static class Even implements Runnable {

        @Override
        public void run() {

            synchronized (lock) {
                while (num <= 100) {
                    if (num % 2 == 0) {
                        System.out.println("偶数:" + num);
                        lock.notify();
                        ++num;
                    } else {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }

        }
    }
}
