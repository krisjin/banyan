package com.concurrent.future;

/**
 * User shijingui
 * Date 2017/5/6
 */
public class FutureData implements Data {
    protected RealData realData = null;//FutureData是RealData的包装
    protected boolean isReady = false;

    public synchronized void setRealData(RealData realData) {
        if (isReady) {
            return;
        }
        this.realData = realData;
        isReady = true;
        notifyAll();//RealData已经被注入，通知getResult()
    }


    @Override
    public synchronized String getResult() {//会等待RealData构造完成
        while (!isReady) {
            try {
                wait();//一直等待，直到RealData被注入
            } catch (InterruptedException e) {

            }
        }
        return realData.getResult();
    }
}
