package com.concurrent.thread.join;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * User: shijingui
 * Date: 2016/9/18
 */
public class Main {

    public static void main(String[] args) {
        AtomicInteger num = new AtomicInteger(1000000);

        Thread calculateThread = new Thread(new CalculateThread(num));
        calculateThread.setName("CalculateThread");

        Thread resultThread = new Thread(new ResultThread(calculateThread));
        resultThread.setName("ResultThread");

        calculateThread.start();
        resultThread.start();
        try {
            resultThread.join();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(num.get());
    }
}
