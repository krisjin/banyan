package org.banyan.concurrent.base.computetask;

/**
 * 计算包装器类
 * @author krisjin
 * @date 2021/1/12
 */
public class Client {

    public static void main(String[] args) {
        ComputeDefault computeDefault = new ComputeDefault();
        ComputeWrapper<Integer, Integer> computeWrapper = new ComputeWrapper<Integer, Integer>(computeDefault);
        try {
            int val = computeWrapper.compute(11);
            System.out.println(">>" + val);
            Thread.sleep(4000);

            val = computeWrapper.compute(11); //第二次从缓存去结果
            System.out.println(val);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
