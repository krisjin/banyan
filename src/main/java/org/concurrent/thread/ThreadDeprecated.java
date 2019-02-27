package org.concurrent.thread;

import org.concurrent.util.SleepUtil;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 线程过时的方法
 * User: krisjin
 * Date: 2016/2/5
 */
public class ThreadDeprecated {

    public static void main(String[] args) {
        DateFormat format = new SimpleDateFormat("HH:mm:ss");
        Thread printThread = new Thread(new Runner(), "PrintThread");
        printThread.setDaemon(true);

        printThread.start();
        SleepUtil.second(3);

        printThread.suspend();
        System.out.println("main suspend PrintThread at " + format.format(new Date()));
        SleepUtil.second(3);

        printThread.resume();
        System.out.println("main resume PrintThread at " + format.format(new Date()));
        SleepUtil.second(3);

        printThread.stop();
        System.out.println("main stop PrintThread at " + format.format(new Date()));
        SleepUtil.second(3);
    }

    static class Runner implements Runnable {
        DateFormat format = new SimpleDateFormat("HH:mm:ss");

        @Override
        public void run() {
            while (true) {
                System.out.println(Thread.currentThread().getName() + " Run at " + format.format(new Date()));
                SleepUtil.second(1);
            }
        }
    }
}
