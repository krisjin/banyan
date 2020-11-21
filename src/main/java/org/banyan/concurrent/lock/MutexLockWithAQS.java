package org.banyan.concurrent.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * @author krisjin
 * @date 2020/11/21
 */
public class MutexLockWithAQS implements Lock, java.io.Serializable {
    // 内部类，自定义同步器
    private static class Sync extends AbstractQueuedSynchronizer {
        // 是否处于占用状态
        protected boolean isHeldExclusively() {
            return getState() == 1;
        }

        // 当状态为0的时候获取锁
        public boolean tryAcquire(int acquires) {
            assert acquires == 1; // Otherwise unused
            if (compareAndSetState(0, 1)) {
                setExclusiveOwnerThread(Thread.currentThread());
                return true;
            }
            return false;
        }

        // 释放锁，将状态设置为0
        protected boolean tryRelease(int releases) {
            assert releases == 1; // Otherwise unused
            if (getState() == 0) throw new IllegalMonitorStateException();
            setExclusiveOwnerThread(null);
            setState(0);
            return true;
        }

        // 返回一个Condition，每个condition都包含了一个condition队列
        Condition newCondition() {
            return new ConditionObject();
        }
    }

    // 仅需要将操作代理到Sync上即可
    private final Sync sync = new Sync();

    public void lock() {
        sync.acquire(1);
    }

    public boolean tryLock() {
        return sync.tryAcquire(1);
    }

    public void unlock() {
        sync.release(1);
    }

    public Condition newCondition() {
        return sync.newCondition();
    }

    public boolean isLocked() {
        return sync.isHeldExclusively();
    }

    public boolean hasQueuedThreads() {
        return sync.hasQueuedThreads();
    }

    public void lockInterruptibly() throws InterruptedException {
        sync.acquireInterruptibly(1);
    }

    public boolean tryLock(long timeout, TimeUnit unit)
            throws InterruptedException {
        return sync.tryAcquireNanos(1, unit.toNanos(timeout));
    }

    static volatile Integer num = 0;

    public static void main(String[] args) throws InterruptedException {
        MutexLockWithAQS mutexLockWithAQS = new MutexLockWithAQS();
        for (int i = 0; i < 3; i++) {
            new Thread(new InnerTask(num, mutexLockWithAQS)).start();
        }
        System.err.println(num);
    }

    static class InnerTask implements Runnable {
        private Integer count;
        private MutexLockWithAQS mutexLockWithAQS;

        public InnerTask(Integer count, MutexLockWithAQS mutexLockWithAQS) {
            this.count = count;
            this.mutexLockWithAQS = mutexLockWithAQS;
        }

        public void run() {
            mutexLockWithAQS.lock();
            count++;
            mutexLockWithAQS.unlock();
        }
    }


}