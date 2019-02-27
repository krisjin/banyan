package org.concurrent.sort;

/**
 * User:krisjin
 * Date:2019/2/24
 */
public class EvenOddOutputThread {
    //从奇数1开始
    private volatile int num = 1;
    //初始奇数现成先输出
    private volatile boolean flag = false;

    public static void main(String[] args) {

        //加锁类
        EvenOddOutputThread evenOddOutputThread = new EvenOddOutputThread();

        Thread evenThread = new Thread(new EvenNum(evenOddOutputThread));
        evenThread.setName("EvenThread-");

        Thread oddThread = new Thread(new OddNum(evenOddOutputThread));
        oddThread.setName("Odd Thread-");

        evenThread.start();
        oddThread.start();
    }

    /**
     * 偶数线程
     */
    public static class EvenNum implements Runnable {
        private EvenOddOutputThread evenOddOutputThread;

        public EvenNum(EvenOddOutputThread evenOddOutputThread) {
            this.evenOddOutputThread = evenOddOutputThread;
        }

        @Override
        public void run() {

            while (evenOddOutputThread.num <= 100) {
                synchronized (EvenOddOutputThread.class) {
                    if (evenOddOutputThread.flag) {
                        System.out.println(Thread.currentThread().getName() + " " + evenOddOutputThread.num);
                        evenOddOutputThread.num++;

                        evenOddOutputThread.flag = false;
                        EvenOddOutputThread.class.notify();

                    } else {
                        try {
                            EvenOddOutputThread.class.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }

            }
        }
    }


    /**
     * 奇数线程
     */
    public static class OddNum implements Runnable {
        private EvenOddOutputThread evenOddOutputThread;

        public OddNum(EvenOddOutputThread evenOddOutputThread) {
            this.evenOddOutputThread = evenOddOutputThread;
        }

        @Override
        public void run() {
            while (evenOddOutputThread.num <= 100) {
                synchronized (EvenOddOutputThread.class) {
                    if (!evenOddOutputThread.flag) {
                        System.out.println(Thread.currentThread().getName() + " " + evenOddOutputThread.num);
                        evenOddOutputThread.num++;

                        evenOddOutputThread.flag = true;

                        EvenOddOutputThread.class.notify();
                    } else {
                        try {
                            EvenOddOutputThread.class.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }
}
