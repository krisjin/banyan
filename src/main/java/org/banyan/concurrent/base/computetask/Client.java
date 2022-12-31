package org.banyan.concurrent.base.computetask;

import org.banyan.concurrent.util.SleepUtil;

public class Client {

    public static void main(String[] args) {
        Computable computable = new ComputeDefault();
        ComputeWrapper<Integer, Integer> computeWrapper = new ComputeWrapper<Integer, Integer>(computable);
        try {
            int val = computeWrapper.compute(11);
            System.out.println("第一次计算值:" + val);
            SleepUtil.second(2);

            val = computeWrapper.compute(11); //第二次从缓存去结果
            System.out.println("第二次计算值:" + val);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
