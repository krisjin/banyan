package org.banyan.concurrent.objectshare;

/**
 * 存在可变的共享变量，非线程安全类。
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
