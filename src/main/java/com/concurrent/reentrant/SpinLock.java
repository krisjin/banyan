package com.concurrent.reentrant;

import java.util.concurrent.atomic.AtomicReference;

/**
 * <p/>
 * User : krisibm@163.com
 * Date: 2015/9/8
 * Time: 16:11
 */
public class SpinLock {

    private AtomicReference<Thread> owner = new AtomicReference<Thread>();

    public void lock() {

        Thread thread = Thread.currentThread();
        while (!owner.compareAndSet(null, thread)) {
        }
    }

    public void unlock() {
        Thread thread = Thread.currentThread();
        owner.compareAndSet(thread, null);
    }

}
