package org.banyan.concurrent.base;

/**
 * 减少线程持有锁的时间，它是一种在并发编程中需要注意的小技巧经验
 * https://mp.weixin.qq.com/s/0mpCtq5CxKWABSR_ASnNCA
 * User: krisjin
 * Date: 2021/6/2
 */
public class SyncMinHoldTimeDemo {
    public void a() {
    }

    public void b() {
    }

    public void mutexMethod() {
    }

    public synchronized void sync1() {
        a();//不存在线程安全影响
        mutexMethod();//需要互斥、保证程序正确性
        b();//不存在线程安全影响
    }

    public void sync2() {
        a();//不存在线程安全影响
        synchronized (this) {
            mutexMethod();//需要互斥、保证程序正确性
        }
        b();//不存在线程安全影响
    }

}
