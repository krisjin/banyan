package org.banyan.concurrent.lock;

import java.util.concurrent.CountDownLatch;

/**
 * 该类主要用于并发同步操作，如果是串行好像用不上这个，子任务执行countDown减一，指导count==0
 * 接着继续执行主线程
 * 倒计时锁存器的同步控制。
 * 使用AQS状态表示计数
 *
 * @author krisjin
 * @date 2020/11/19
 */
public class CountDownLatchDemo {

    public static void main(String[] args) {

        //创建减少的latch, 初始参数是10
        CountDownLatch countDownLatch = new CountDownLatch(10);
        for (int i = 0; i < 10; i++) {
            countDownLatch.countDown();
        }


    }
}
