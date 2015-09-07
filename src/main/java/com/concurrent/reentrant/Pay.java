package com.concurrent.reentrant;

/**
 * <p/>
 * User : krisibm@163.com
 * Date: 2015/9/7
 * Time: 22:47
 */
public class Pay {

    public synchronized void pay() {

        System.out.println("支付父类");

    }

}
