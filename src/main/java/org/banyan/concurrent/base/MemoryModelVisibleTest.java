package org.banyan.concurrent.base;

/**
 * User:krisjin
 * Date:2020-04-16
 */
public class MemoryModelVisibleTest {
    private int i = 0;
    private int j = 0;
    // 当前的k被设置为volatile ,i,j变量都将被设置为happens-before关系
    // 确保k被更改后，之前的i,j都已经保证被赋值了, 变量的顺序
    private volatile boolean k = false;

    //线程1设置新值
    private void thread1() {
        new Thread(() -> {
            i = 1;
            j = 2;
            k = true;
        }).start();
    }

    //线程2读取新值
    private void thread2() {
        new Thread(() -> {
            String s = "i=" + i + ", j=" + j;//i ,j 从主内存获取最新并更新到本地内存
            System.out.println(s);
        }).start();
    }


}
