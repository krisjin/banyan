package org.banyan.concurrent.jvm;

/**
 * 递归调用
 * <pre>
 * -Xss128K
 * deep of calling = 701
 * java.lang.StackOverflowError
 *
 * -Xss256K
 * deep of calling = 1817
 * java.lang.StackOverflowError
 *
 * -Xss
 * 通常只有几百K
 * 决定了函数调用的深度
 * 每个线程都有独立的栈空间
 * 局部变量、参数 分配在栈上
 *
 * 如果堆空间没有用完也抛出了OOM，有可能是永久区导致的
 *
 * Xmx20m -Xms5m -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=/usr/local/a.dump
 *
 *
 * -Xmn
 *      设置新生代大小
 * -XX:NewRatio
 *      新生代（eden+2*s）和老年代（不包含永久区）的比值
 *      4 表示 新生代:老年代=1:4，即年轻代占堆的1/5
 * -XX:SurvivorRatio
 *      设置两个Survivor区和eden的比
 *      8表示 两个Survivor :eden=2:8，即一个Survivor占年轻代的1/10
 *
 * </pre>
 */
public class TestStackDeep {

    private static int count = 0;

    public static void recursion(long a, long b, long c) {
        long e = 1, f = 2, g = 3, h = 4, i = 5, k = 6, q = 7, x = 8, y = 9, z = 10;
        count++;
        recursion(a, b, c);
    }

    public static void main(String args[]) {
        try {
            recursion(0L, 0L, 0L);


        } catch (Throwable e) {
            System.out.println("deep of calling = " + count);
            e.printStackTrace();
        }
    }

}
