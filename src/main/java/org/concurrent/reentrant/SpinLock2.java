package org.concurrent.reentrant;

import java.util.concurrent.atomic.AtomicReference;

/**
 * 采用计数次进行统计，解决了自旋锁的死锁问题，该自旋锁即为可重入锁。
 * <p/>
 * User : krisibm@163.com
 * Date: 2015/9/8
 * Time: 16:21
 */
public class SpinLock2 {

    private AtomicReference<Thread> owner = new AtomicReference<Thread>();

    private int count = 0;

    public void lock() {
        Thread currentThread = Thread.currentThread();

        if (currentThread == owner.get()) {
            count++;
            return;
        }

        while (!owner.compareAndSet(null, currentThread)) {
        }
    }

    public void unlock() {
        Thread currentThread = Thread.currentThread();
        if (owner.get() == currentThread) {
            if (count != 0) {
                count--;
            } else {
                owner.compareAndSet(currentThread, null);
            }

        }
    }
}
