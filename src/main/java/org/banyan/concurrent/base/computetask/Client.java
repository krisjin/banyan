package org.banyan.concurrent.base.computetask;

import org.banyan.concurrent.util.SleepUtil;

import java.util.concurrent.ExecutionException;

/**
 * 这是什么
 */
public class Client {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Computable computable = new ComputeDefault();//实例化默认计算实现
        //创建一个缓存计算包装器，包装器的构造参数为计算接口
        ComputeWrapper<Integer, Integer> computeWrapper = new ComputeWrapper<Integer, Integer>(computable);
        int val = computeWrapper.compute(11);
        System.out.println("第一次计算值:" + val);
        SleepUtil.second(2);
        val = computeWrapper.compute(12); //第二次从缓存获取
        System.out.println("第二次计算值:" + val);

    }
}
