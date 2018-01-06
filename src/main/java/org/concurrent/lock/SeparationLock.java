package org.concurrent.lock;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 分离锁,使用{@link ReentrantReadWriteLock}
 * <p/>
 * User : krisibm@163.com
 * Date: 2015/9/14
 * Time: 9:29
 */
public class SeparationLock {

    private int seg = 16;

    private final Map[] maps = null;

    private final ReentrantReadWriteLock[] lock = null;

    public SeparationLock(int seg) {
        this.seg = seg;
        init();
    }

    public SeparationLock() {
        init();
    }


    private int hashKey(Object key) {
        return key.hashCode() % seg;
    }


    /**
     * 初始化
     */
    private void init() {
        for (int i = 0; i < seg; i++) {
            maps[i] = new HashMap();
            lock[i] = new ReentrantReadWriteLock();
        }
    }

    /**
     * @param key
     * @param value
     */
    public void put(Object key, Object value) {
        int i = hashKey(key);
        lock[i].writeLock().lock();
        try {
            maps[i].put(key, value);
        } finally {
            lock[i].writeLock().unlock();
        }
    }

    public Object get(Object key) {
        int i = hashKey(key);
        Object value = null;
        lock[i].readLock().lock();
        try {
            value = maps[i].get(key);
        } finally {
            lock[i].readLock().unlock();
        }
        return value;
    }

}
