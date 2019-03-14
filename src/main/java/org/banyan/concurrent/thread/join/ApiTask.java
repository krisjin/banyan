package org.banyan.concurrent.thread.join;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * User: krisjin
 * Date: 2016/9/18
 */
public class ApiTask implements Runnable {
    private final List<Ware> wareList;

    //构造函数传入共享集合
    public ApiTask(final List wareList) {
        this.wareList = wareList;
    }

    public void run() {
        try {
            //模拟网络随机开销
            TimeUnit.MILLISECONDS.sleep(new Random().nextInt(2000));
            //模拟服务接口数据获取
            Ware ware = new Ware();
            ware.setPrice(11.11);
            wareList.add(ware);
            System.out.println(Thread.currentThread().getId() + " 线程调用服务接口结束...");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}
