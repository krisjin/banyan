package org.banyan.concurrent.future.simple;

/**
 * User krisjin
 * Date 2017/5/6
 */
public class Client {
    private FutureBizData futureBizData = new FutureBizData();

    public static void main(String[] args) {
        Client client = new Client();
        FutureBizData data = client.request("kris");//立即返回

        System.out.println("获取业务数据任务已提交并返回");
        System.out.println("data=" + data.getResult());
    }


    public FutureBizData request(final String queryStr) {
        new Thread() {
            public void run() {
                BizData bizData = new BizData(queryStr);
                futureBizData.setBizData(bizData);
            }
        }.start();
        return futureBizData;
    }


}
