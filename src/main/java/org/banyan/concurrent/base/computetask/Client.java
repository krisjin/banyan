package org.banyan.concurrent.base.computetask;

/**
 * @author krisjin
 * @date 2021/1/12
 */
public class Client {

    public static void main(String[] args) {

        ComputeDefault computeDefault = new ComputeDefault();
        ComputeWrapper<Integer, Integer> computeWrapper = new ComputeWrapper<Integer, Integer>(computeDefault);
        try {
            computeWrapper.compute(11);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
