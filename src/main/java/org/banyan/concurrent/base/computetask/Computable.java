package org.banyan.concurrent.base.computetask;

/**
 * 计算接口
 * User : krisjin
 * Date: 2015/9/15
 */
public interface Computable<A, V> {

    V compute(A arg) throws InterruptedException;

}
