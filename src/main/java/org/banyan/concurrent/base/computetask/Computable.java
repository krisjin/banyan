package org.banyan.concurrent.base.computetask;

import java.util.concurrent.ExecutionException;

/**
 * 计算接口
 */
public interface Computable<A, V> {

    V compute(A arg) throws InterruptedException, ExecutionException;

}
