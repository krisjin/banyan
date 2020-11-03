package org.banyan.concurrent.threadlocal;

/**
 * User: krisjin
 * Date: 2016/2/1
 */
public class TLExam1 {
    private static ThreadLocal<String> strThreadLocal = new ThreadLocal<>();


    public void setPin(String pin) {
        strThreadLocal.set(pin + ": " + Thread.currentThread().getId() + ": " + Thread.currentThread().getName());
    }

    public String getPin() {
        return strThreadLocal.get();
    }

    public static void main(String[] args) throws InterruptedException {
        TLExam1 TLExam1 = new TLExam1();
        TLExam1.setPin("2020");

        System.out.println(TLExam1.getPin());

        Thread t1 = new Thread(new MyRunnable(TLExam1));


        t1.start();
        t1.join();
        System.out.println(TLExam1.getPin());
    }

    static class MyRunnable implements Runnable {
        TLExam1 TLExam1;

        public MyRunnable(TLExam1 TLExam1) {
            this.TLExam1 = TLExam1;
        }

        public void run() {
            TLExam1.setPin("2021");
            System.out.println(TLExam1.getPin());
        }
    }
}
