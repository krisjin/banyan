package org.banyan.concurrent.base.computetask;

import org.banyan.concurrent.util.SleepUtil;

/**
 * 计算包装器类
 *
 * @author krisjin
 * @date 2021/1/12
 */
public class Client {

    public static void main(String[] args) {
        Computable computeDefault = new ComputeDefault();
        ComputeWrapper<Integer, Integer> computeWrapper = new ComputeWrapper<Integer, Integer>(computeDefault);
        try {
            int val = computeWrapper.compute(11);
            System.out.println("第一次计算值:" + val);
            SleepUtil.second(2);

            val = computeWrapper.compute(10); //第二次从缓存去结果
            System.out.println(val);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
