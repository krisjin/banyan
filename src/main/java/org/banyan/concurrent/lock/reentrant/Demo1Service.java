package org.banyan.concurrent.lock.reentrant;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Demo1Service {
    private Lock lock = new ReentrantLock();

    public void methodA() {
        lock.lock();
        for (int i = 0; i < 5; i++) {
            System.out.println("ThreadName=" + Thread.currentThread().getName() + " A method:" + (i + 1));
        }
        lock.unlock();
    }

    public void methodB() {
        lock.lock();
        for (int i = 0; i < 5; i++) {
            System.out.println("ThreadName=" + Thread.currentThread().getName() + " B method:" + (i + 1));
        }
        lock.unlock();
    }

}
