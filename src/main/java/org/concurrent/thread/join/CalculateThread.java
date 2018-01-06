package org.concurrent.thread.join;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * User: shijingui
 * Date: 2016/9/18
 */
public class CalculateThread implements Runnable {
    private final AtomicInteger num;

    public CalculateThread(final AtomicInteger num) {
        this.num = num;
    }

    public void run() {
        int result = 0;
        for (int i = 0; i <= num.get(); i++) {
            result += i;
        }
        num.set(result);
        System.out.println(Thread.currentThread().getName() + " end.");
    }

}
