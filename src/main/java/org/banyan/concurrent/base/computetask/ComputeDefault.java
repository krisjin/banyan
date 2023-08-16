package org.banyan.concurrent.base.computetask;

import org.banyan.concurrent.util.SleepUtil;

/**
 * 默认计算功能实现
 */
public class ComputeDefault implements Computable<Integer, Integer> {

    @Override
    public Integer compute(Integer param) {
        int result = param + 1;
        SleepUtil.second(3);
        return result;
    }
}
