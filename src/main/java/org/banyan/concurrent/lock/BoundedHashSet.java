package org.banyan.concurrent.lock;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.Semaphore;

/**
 * 使用Semaphore为容器设置边界
 * <p/>
 *
 * @User : krisjin
 * @Date: 2015/9/15
 */
public class BoundedHashSet<T> {
    private final Set<T> set;
    private final Semaphore semaphore;

    public BoundedHashSet(int bound) {
        this.set = Collections.synchronizedSet(new HashSet<T>());
        this.semaphore = new Semaphore(bound);
    }

    public boolean add(T o) throws InterruptedException {
        boolean result = false;
        try {
            semaphore.acquire();
            result = set.add(o);
            return result;
        } finally {
            if (!result) {
                semaphore.release();
            }
        }
    }

    public boolean remove(T o) {
        boolean result = set.remove(o);
        if (result) {
            semaphore.release();
        }
        return result;
    }

    public static void main(String[] args) {
        int i = 1;
        while (true) {
            if (i % 100 == 0) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            i++;
        }
    }
}
