package org.banyan.concurrent.reentrant;

import java.util.concurrent.locks.ReentrantLock;

/**
 * User : krisjin
 * Date: 2015/9/8
 */
public class ReentrantLockDemo implements Runnable {

    private ReentrantLock lock = new ReentrantLock();

    public static void main(String[] args) {
        ReentrantLockDemo reentrantLockDemo = new ReentrantLockDemo();
        new Thread(reentrantLockDemo).start();
        new Thread(reentrantLockDemo).start();
    }

    public void get() {
        lock.lock();
        System.out.println(Thread.currentThread().getId());
        set();
        lock.unlock();
    }

    public void set() {
        lock.lock();
        System.out.println(Thread.currentThread().getId());
        lock.unlock();
    }

    @Override
    public void run() {
        get();
    }

}
