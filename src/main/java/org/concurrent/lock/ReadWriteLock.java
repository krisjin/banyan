package org.concurrent.lock;

import java.util.HashMap;
import java.util.Map;

/**
 * 读写锁
 * User: krisjin
 * Date: 2016/2/24
 */
public class ReadWriteLock {
    private Map<Thread, Integer> readingThreads = new HashMap<Thread, Integer>();

    private int writeAccesses = 0;
    private int writeRequests = 0;
    private Thread writingThread = null;

    /**
     * 获取读锁
     *
     * @throws InterruptedException
     */
    public synchronized void lockRead() throws InterruptedException {
        //当前读线程
        Thread callingThread = Thread.currentThread();
        while (!canGrantReadAccess(callingThread)) {
            wait();
        }
        readingThreads.put(callingThread, (getReadAccessCount(callingThread) + 1));
    }

    /**
     * 释放读锁
     */
    public synchronized void unlockRead() {
        Thread callingThread = Thread.currentThread();
        if (!isReader(callingThread)) {
            throw new IllegalMonitorStateException("Calling Thread does not hold a read lock on this ReadWriteLock");
        }
        int accessCount = getReadAccessCount(callingThread);
        if (accessCount == 1) {
            readingThreads.remove(callingThread);
        } else {
            readingThreads.put(callingThread, (accessCount - 1));
        }
        notifyAll();
    }

    /**
     * 释放允许访问读锁
     *
     * @param callingThread
     * @return
     */
    private boolean canGrantReadAccess(Thread callingThread) {
        if (isWriter(callingThread)) return true;
        if (hasWriter()) return false;
        if (isReader(callingThread)) return true;
        if (hasWriteRequests()) return false;
        return true;
    }

    /**
     * 获取写锁
     *
     * @throws InterruptedException
     */
    public synchronized void lockWrite() throws InterruptedException {
        writeRequests++;
        Thread callingThread = Thread.currentThread();
        while (!canGrantWriteAccess(callingThread)) {
            wait();
        }
        writeRequests--;
        writeAccesses++;
        writingThread = callingThread;
    }

    public synchronized void unlockWrite() throws InterruptedException {
        if (!isWriter(Thread.currentThread())) {
            throw new IllegalMonitorStateException("Calling Thread does not" + " hold the write lock on this ReadWriteLock");
        }
        writeAccesses--;
        if (writeAccesses == 0) {
            writingThread = null;
        }
        notifyAll();
    }

    private boolean canGrantWriteAccess(Thread callingThread) {
        if (isOnlyReader(callingThread)) return true;
        if (hasReaders()) return false;
        if (writingThread == null) return true;
        if (!isWriter(callingThread)) return false;
        return true;
    }

    /**
     * 过去读线程访问次数
     *
     * @param callingThread
     * @return
     */
    private int getReadAccessCount(Thread callingThread) {
        Integer accessCount = readingThreads.get(callingThread);
        if (accessCount == null) return 0;
        return accessCount.intValue();
    }

    /**
     * 是否有读线程
     *
     * @return
     */
    private boolean hasReaders() {
        return readingThreads.size() > 0;
    }

    /**
     * 判断当前读线程是否存在
     *
     * @param callingThread
     * @return
     */
    private boolean isReader(Thread callingThread) {
        return readingThreads.get(callingThread) != null;
    }

    /**
     * 是否有唯一一个读线程
     *
     * @param callingThread
     * @return
     */
    private boolean isOnlyReader(Thread callingThread) {
        return readingThreads.size() == 1 && readingThreads.get(callingThread) != null;
    }

    /**
     * 判断写线程是否为空
     *
     * @return
     */
    private boolean hasWriter() {
        return writingThread != null;
    }

    /**
     * 判断当前线程是不是写线程
     *
     * @param callingThread
     * @return
     */
    private boolean isWriter(Thread callingThread) {
        return writingThread == callingThread;
    }

    /**
     * 是否存在些请求
     *
     * @return
     */
    private boolean hasWriteRequests() {
        return this.writeRequests > 0;
    }
}
