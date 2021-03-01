package org.banyan.concurrent.base.computetask;

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
    public Integer compute(Integer arg) throws InterruptedException {
        //假装经过长时间的计算后
        int result = arg + 1;
        return result;
    }
}
