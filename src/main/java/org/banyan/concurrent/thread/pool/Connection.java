package org.banyan.concurrent.thread.pool;

import org.banyan.concurrent.util.SleepUtil;

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
