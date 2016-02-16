package com.concurrent.queue;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 有界阻塞缓冲队列
 * User: shijingui
 * Date: 2016/2/16
 */
public class BoundedBuffferQueue<E> {
    private Object[] buffer;

    private final ReentrantLock lock;
    private final Condition notEmpty;
    private final Condition notFull;

    /**
     * 初始化队列大小
     *
     * @param size
     */
    public BoundedBuffferQueue(int size) {
        buffer = new Object[size];
        lock = new ReentrantLock();
        notEmpty = lock.newCondition();
        notFull = lock.newCondition();
    }

    public void put(E e) {

    }

    public E take() {

        return null;
    }
}
