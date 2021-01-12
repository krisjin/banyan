package org.banyan.concurrent.base.computetask;

/**
 * User : krisjin
 * Date: 2015/9/15
 */
public class ComputeDefault implements Computable<Integer, Integer> {
    @Override
    public Integer compute(Integer arg) throws InterruptedException {
        //假装经过长时间的计算后
        int result = arg + 1;
        return result;
    }
}
