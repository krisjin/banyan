package com.concurrent.thread;

import java.util.concurrent.TimeUnit;

/**
 * User: shijingui
 * Date: 2016/2/4
 */
public class SleepUtil {

    public static final void second(long second) {
        try {
            TimeUnit.SECONDS.sleep(second);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static final void millisecond(long millisecond) {
        try {
            TimeUnit.MILLISECONDS.sleep(millisecond);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    
}
