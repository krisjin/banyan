package org.banyan.concurrent.thread.pool;

import java.util.LinkedList;

/**
 * User: krisjin
 * Date: 2016/2/17
 */
public class ConnectionPool {

    private static final int MAX_SIZE = 20;
    private LinkedList<Connection> pool = new LinkedList<Connection>();

    /**
     * 初始化指定大小的连接池
     *
     * @param initialSize
     */
    public ConnectionPool(int initialSize) {
        if (initialSize > 0) {
            for (int i = 0; i < initialSize; i++) {
                pool.addLast(new Connection());
            }
        }
    }

    public void releaseConnection() throws InterruptedException {
        synchronized (pool) {
            while (pool.size() >= MAX_SIZE) {
                pool.wait();
            }
            //添加后需要进行通知，这样其它消费者能够感知到连接池中已经增加一个连接
            pool.addLast(new Connection());
            pool.notifyAll();
        }
    }

    /**
     * 超时获取连接
     *
     * @param millisecond
     * @return
     * @throws InterruptedException
     */
    public Connection fetchConnection(long millisecond) throws InterruptedException {
        synchronized (pool) {
            if (millisecond <= 0) {
                while (pool.size() == 0) {
                    pool.wait();
                }
                return pool.removeFirst();
            } else {
                long futureTime = System.currentTimeMillis() + millisecond;
                long deltaTime = millisecond;

                while (pool.isEmpty() && millisecond > 0) {
                    pool.wait(deltaTime);
                    deltaTime = futureTime - System.currentTimeMillis();
                }
                Connection conn = null;
                if (!pool.isEmpty()) {
                    conn = pool.removeFirst();
                }
                return conn;
            }
        }
    }
}
