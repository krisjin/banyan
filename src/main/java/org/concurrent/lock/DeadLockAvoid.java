package org.concurrent.lock;

/**
 * <p>
 * 在有些情况下死锁是可以避免的,介绍三种用于避免死锁的技术：
 * 1.加锁顺序
 * 2.加锁时限
 * 3.死锁检测
 * <br><br>
 * 加锁顺序：
 * 当多个线程需要相同的一些锁，但是按照不同的顺序加锁，死锁就很容易发生。
 * 如果能确保所有的线程都是按照相同的顺序获得锁，那么死锁就不会发生。看下面这个例子：
 * Thread 1: lock Demo1Main lock B
 * Thread 2: wait for Demo1Main  ,lock C(获取A锁后，才能继续获取C锁)
 * Thread 3: wait for Demo1Main ,wait for B , wait for C
 * 如果一个线程（比如线程3获取锁），那么它必须按照确定的顺序获取锁
 * <p/>
 * </p>
 * <p/>
 * <p/>
 * User: krisjin
 * Date: 2016/1/26
 */
public class DeadLockAvoid {


}
