package org.concurrent.future.internal;

import org.concurrent.util.SleepUtil;

import java.util.concurrent.Callable;

/**
 * User shijingui
 * Date 2017/5/10
 */
public class RealData implements Callable<String> {
    private String data;

    public RealData(String data) {
        this.data = data;
    }

    @Override
    public String call() throws Exception {
        StringBuilder sb = new StringBuilder(10);
        for (int i = 0; i < 20; i++) {
            sb.append(data);
        }
        SleepUtil.second(1);
        System.out.println("业务逻辑执行完成...");
        return sb.toString();
    }
}
