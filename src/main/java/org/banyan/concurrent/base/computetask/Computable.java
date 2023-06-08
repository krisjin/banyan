package org.banyan.concurrent.base.computetask;

import java.util.concurrent.ExecutionException;

/**
 * 计算接口定义
 * 接口定义，A为参数，V为定义的结果
 */
public interface Computable<A, V> {

    V compute(A param) throws InterruptedException, ExecutionException;

}
