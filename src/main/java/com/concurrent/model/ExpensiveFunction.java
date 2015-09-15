package com.concurrent.model;

import java.math.BigInteger;

/**
 * <p/>
 * User : krisibm@163.com
 * Date: 2015/9/15
 * Time: 22:31
 */
public class ExpensiveFunction implements Computable<String, BigInteger> {
    @Override
    public BigInteger compute(String arg) throws InterruptedException {
        //经过长时间的计算后
        return new BigInteger(arg);
    }
}
