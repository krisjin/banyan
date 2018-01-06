package org.concurrent.thread.pool;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 带名称的线程生成工厂,支持设置名称，设置是否为守护线程
 */
public class NamedThreadFactory implements ThreadFactory {

    /**
     * 系统全局线程池计数器
     */
    private static final AtomicInteger poolCount = new AtomicInteger();
    /**
     * 当前线程池计数器
     */
    final AtomicInteger threadCount = new AtomicInteger(1);
    final ThreadGroup group;
    final String namePrefix;
    final boolean isDaemon; //是否守护线程，true的话随主线程退出而退出，false的话则要主动退出

    /**
     * 构造函数，默认非守护线程
     *
     * @param prefix 前缀，后面会自动加上-T-
     */
    public NamedThreadFactory(String prefix) {
        this(prefix, false);
    }

    /**
     * 构造函数
     *
     * @param prefix 前缀，后面会自动加上-T-
     * @param daemon 是否守护线程，true的话随主线程退出而退出，false的话则要主动退出
     */
    public NamedThreadFactory(String prefix, boolean daemon) {
        SecurityManager s = System.getSecurityManager();
        group = (s != null) ? s.getThreadGroup() : Thread.currentThread().getThreadGroup();
        namePrefix = prefix + "-" + poolCount.getAndIncrement() + "-T-";
        isDaemon = daemon;
    }

    @Override
    public Thread newThread(Runnable r) {
        Thread t = new Thread(group, r, namePrefix + threadCount.getAndIncrement(), 0);
        t.setDaemon(isDaemon);
        if (t.getPriority() != Thread.NORM_PRIORITY) {
            t.setPriority(Thread.NORM_PRIORITY);
        }
        return t;
    }
}

