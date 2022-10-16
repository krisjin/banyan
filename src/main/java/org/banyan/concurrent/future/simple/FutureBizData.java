package org.banyan.concurrent.future.simple;

public class FutureBizData {
    //FutureData是RealData的包装
    protected BizData bizData = null;
    protected boolean isReady = false;

    public synchronized void setBizData(BizData bizData) {
        if (isReady) {
            return;
        }
        this.bizData = bizData;
        isReady = true;
        notifyAll();//RealData已经被注入，通知getResult()
    }

    public synchronized String getResult() {//会等待RealData构造完成
        while (!isReady) {
            try {
                wait();//一直等待，直到RealData被注入
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return bizData.getResult();
    }
}
