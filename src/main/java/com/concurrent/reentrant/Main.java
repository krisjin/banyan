package com.concurrent.reentrant;

/**
 * Java 提供的同步机制支持锁重入，以避免死锁。
 * 当一个线程持有对象锁时，当再尝试去持有已经占有的锁时，会以线程的方式重新持有对象锁。
 * 当下的WeixinPay继承了Pay,在WeixinPay中重写了pay方法，并调用父类中的pay方法，因为两次调用的pay方法都是同步的。
 * <p/>
 * User : krisibm@163.com
 * Date: 2015/9/7
 * Time: 22:49
 */
public class Main {

    public static void main(String[] args) {

        WeixinPay weixinPay = new WeixinPay();
        weixinPay.pay();
    }

}
