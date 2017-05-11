package com.concurrent.future;

import com.concurrent.util.SleepUtil;

/**
 * User shijingui
 * Date 2017/5/6
 */
public class Test {
    public static void main(String[] args) {
        Client client = new Client();
        //这里会立即返回，因为得到的是一个FutureData而不是RealData
        Data data = client.request("name");
        System.out.println("请求完毕...");
        //使用sleep代替其它业务操作
        SleepUtil.second(1);
        System.out.println("其它业务执行完毕...");
        System.out.println("data=" + data.getResult());
    }
}
