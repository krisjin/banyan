package com.concurrent.thread.pool;

import com.concurrent.util.SleepUtil;

/**
 * User: shijingui
 * Date: 2016/2/17
 */
public class Connection {
    public void sendStatement() {
        SleepUtil.millisecond(10);
        System.out.println(Thread.currentThread().getName() + " send statement.");
    }
}
