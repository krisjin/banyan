package org.banyan.concurrent.lock;

/**
 * User:krisjin
 * Date:2019/4/26
 */
public class SimpleLock {

    private boolean isLocked = false;

    public synchronized void lock() throws InterruptedException {

        while (isLocked) {
            wait();
        }
        isLocked = true;

    }


    public synchronized void unlock() {
        isLocked = false;
        notify();
    }

}
