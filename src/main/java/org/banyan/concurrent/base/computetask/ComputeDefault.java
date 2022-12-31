package org.banyan.concurrent.base.computetask;

import org.banyan.concurrent.util.SleepUtil;

/**
 * 默认计算任务
 */
public class ComputeDefault implements Computable<Integer, Integer> {

    /**
     * 计算任务
     *
     * @param arg
     * @return
     * @throws InterruptedException
     */
    @Override
    public Integer compute(Integer arg) {
        int result = arg + 1;
        SleepUtil.second(3);
        return result;
    }
}
