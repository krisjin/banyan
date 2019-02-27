package org.concurrent.future;

/**
 * User krisjin
 * Date 2017/5/6
 */
public class Client {
    final FutureData futureData = new FutureData();

    public Data request(final String queryStr) {
        //ReadData构建很慢，使用一个单独线程
        new Thread() {
            public void run() {
                RealData realData = new RealData(queryStr);
                futureData.setRealData(realData);
            }
        }.start();
        return futureData;
    }


}
