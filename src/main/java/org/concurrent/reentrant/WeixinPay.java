package org.concurrent.reentrant;

/**
 * <p/>
 * User : krisibm@163.com
 * Date: 2015/9/7
 * Time: 22:48
 */
public class WeixinPay extends Pay {

    public static void main(String[] args) {
        WeixinPay weixinPay = new WeixinPay();
        weixinPay.pay();
    }

    @Override
    public synchronized void pay() {
        System.out.println("微信支付......");
        super.pay();
    }
}


class Pay {

    public synchronized void pay() {

        System.out.println("支付父类");

    }

}
