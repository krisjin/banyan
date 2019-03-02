package org.concurrent.lock.reentrant;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author krisjin on 2019/2/28
 */
public class Demo1 {

    private Lock lock = new ReentrantLock();

    public static void main(String[] args) {

    }

    public void t() {
        lock.lock();

        for (int i = 0; i < 5; i++) {
            System.out.println("ThreadName=" + Thread.currentThread().getName() + " i:" + (i + 1));
        }
        lock.unlock();
    }

}
