package org.banyan.concurrent.future.internal;

import org.banyan.concurrent.util.SleepUtil;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * User krisjin
 * Date 2017/5/10
 */
public class RealData implements Callable<String> {
    private String data;
    ExecutorService cachedThreadPool = Executors.newCachedThreadPool();

    public RealData(String data) {
        this.data = data;
    }

    @Override
    public String call() {
        StringBuilder sb = new StringBuilder(10);
        for (int i = 0; i < 20; i++) {
            sb.append(data).append(",");
        }
        SleepUtil.second(1);
        System.out.println("业务逻辑执行完成...");
        return sb.toString();
    }
}
