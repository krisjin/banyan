package org.banyan.concurrent.base.computetask;

import org.banyan.concurrent.util.SleepUtil;

/**
 * 将计算任务进行独立封装
 * User : krisjin
 * Date: 2015/9/15
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
        //假装经过长时间的计算后
        int result = arg + 1;
        SleepUtil.second(3);
        return result;
    }
}
