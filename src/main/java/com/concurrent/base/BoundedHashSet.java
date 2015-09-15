package com.concurrent.base;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.Semaphore;

/**
 * 使用Semaphore为容器设置边界
 * <p/>
 * User : krisibm@163.com
 * Date: 2015/9/15
 * Time: 21:34
 */
public class BoundedHashSet<T> {

    private final Set<T> set;
    private final Semaphore sem;

    public BoundedHashSet(int bound) {
        this.set = Collections.synchronizedSet(new HashSet<T>());
        this.sem = new Semaphore(bound);
    }

    public boolean add(T o) throws InterruptedException {
        sem.acquire();
        boolean result = false;
        try {
            result = set.add(o);
            return result;
        } finally {
            if (!result) {
                sem.release();
            }
        }
    }

    public boolean remove(T o) {
        boolean result = set.remove(o);
        if (result) {
            sem.release();
        }
        return result;
    }
}
