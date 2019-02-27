package org.concurrent.model;

/**
 * 计算接口
 * <p/>
 * User : krisjin
 * Date: 2015/9/15
 * Time: 22:29
 */
public interface Computable<A, V> {

    public V compute(A arg) throws InterruptedException;
}
