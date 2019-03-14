package org.banyan.concurrent.lock;

import java.util.HashMap;
import java.util.Map;

/**
 * synchronized同步作用域的大小，对性能有影响。
 * <p/>
 * User : krisjin
 * Date: 2015/9/14
 * Time: 13:54
 */
public class TimeConsumingLock implements Runnable {

    private final Map<Object, Object> map = new HashMap<Object, Object>();

    private volatile int flag = 1;

    public TimeConsumingLock(int flag) {
        this.flag = flag;
    }

    public static void main(String[] args) {
        TimeConsumingLock timeConsumingLock = new TimeConsumingLock(5000000);
        Thread thread = new Thread(timeConsumingLock);
        long startTime = System.currentTimeMillis();

        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long costTime = System.currentTimeMillis() - startTime;
        System.out.println(costTime);

    }

    public synchronized void write(Object key) {
        if (null == key) {
            return;
        }
        map.put(key, "value");

    }

    public synchronized void write2(Object key) {
        if (null == key) {
            return;
        }
        synchronized (this) {
            map.put(key, "val");
        }

    }

    @Override
    public void run() {
        for (int i = 0; i < flag; i++) {
//           write(i);
            write2(i);
        }
    }
}
