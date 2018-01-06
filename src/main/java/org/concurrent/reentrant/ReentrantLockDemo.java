package org.concurrent.reentrant;

import java.util.concurrent.locks.ReentrantLock;

/**
 * <p/>
 * User : krisibm@163.com
 * Date: 2015/9/8
 * Time: 15:56
 */
public class ReentrantLockDemo implements Runnable {

    private ReentrantLock lock = new ReentrantLock();

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

    public static void main(String[] args) {
        ReentrantLockDemo demo02 = new ReentrantLockDemo();
        new Thread(demo02).start();
        new Thread(demo02).start();
    }

}
