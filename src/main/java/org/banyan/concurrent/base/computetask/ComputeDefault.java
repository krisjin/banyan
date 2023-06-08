package org.banyan.concurrent.base.computetask;

import org.banyan.concurrent.util.SleepUtil;

public class ComputeDefault implements Computable<Integer, Integer> {

    @Override
    public Integer compute(Integer param) {
        int result = param + 1;
        SleepUtil.second(3);
        return result;
    }
}
