package org.concurrent.sort;

/**
 * User:shijingui
 * Date:2019/2/24
 *  
 */
public class TwoThreadWaitNotify {
    private int start = 1;

    private boolean flag = false;

    public static void main(String[] args) {
//        Object obj
        TwoThreadWaitNotify twoThread = new TwoThreadWaitNotify();

        Thread evenThread = new Thread(new EvenNum(twoThread));
        evenThread.setName("EvenThread-");

        Thread oddThread = new Thread(new OddNum(twoThread));
        oddThread.setName("Odd Thread-");

        evenThread.start();
        oddThread.start();
    }

    /**
     * 偶数线程
     */
    public static class EvenNum implements Runnable {
        private TwoThreadWaitNotify number;

        public EvenNum(TwoThreadWaitNotify number) {
            this.number = number;
        }

        @Override
        public void run() {

            while (number.start <= 100) {
                synchronized (TwoThreadWaitNotify.class) {
                    if (number.flag) {
                        System.out.println(Thread.currentThread().getName() + "    偶数   " + number.start);
                        number.start++;

                        number.flag = false;
                        TwoThreadWaitNotify.class.notify();

                    } else {
                        try {
                            TwoThreadWaitNotify.class.wait();
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
        private TwoThreadWaitNotify number;

        public OddNum(TwoThreadWaitNotify number) {
            this.number = number;
        }

        @Override
        public void run() {
            while (number.start <= 100) {
                synchronized (TwoThreadWaitNotify.class) {
                    if (!number.flag) {
                        System.out.println(Thread.currentThread().getName() + "   奇数   " + number.start);
                        number.start++;

                        number.flag = true;
                        if (number.start == 1) {

                        }


                        TwoThreadWaitNotify.class.notify();
                    } else {
                        try {
                            TwoThreadWaitNotify.class.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }
}
