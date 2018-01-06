package org.concurrent.demo01;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author krisjin
 * @date 2015-1-27
 */
public class WorkThread implements Runnable {

    AtomicInteger atomInteger = new AtomicInteger(0);

    public void run() {
        System.out.println(Thread.currentThread().getName() + " counts:" + atomInteger.incrementAndGet());
    }

}
