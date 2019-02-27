package org.concurrent.thread.pool;

import org.concurrent.util.SleepUtil;

/**
 * User: krisjin
 * Date: 2016/2/17
 */
public class Connection {
    public void sendStatement() {
        SleepUtil.millisecond(10);
        System.out.println(Thread.currentThread().getName() + " send statement.");
    }
}
