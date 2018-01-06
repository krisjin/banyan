package org.concurrent.thread.interrupt;

import java.util.concurrent.locks.ReentrantLock;

public class BufferInterruptibly {

    private ReentrantLock lock = new ReentrantLock();

    public void write() {
        lock.lock();
        try {
            long startTime = System.currentTimeMillis();
            System.out.println("开始往缓冲区写入数据...");
            for (; ; ) {
                if (System.currentTimeMillis() - startTime > Integer.MAX_VALUE) {

                    break;
                }
            }
            System.out.println("缓冲区写数据结束...");
        } finally {
            lock.unlock();

        }
    }

    public void read() throws InterruptedException {
        try {
            lock.lockInterruptibly();

            System.out.println("从缓冲区读数据...");

        } finally {
            lock.unlock();
        }

    }

}
