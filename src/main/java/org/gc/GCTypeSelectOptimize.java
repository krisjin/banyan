package org.gc;

import org.concurrent.util.SleepUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * 配置一：
 * 1G的堆（-Xms1g -Xmx1g）
 * 使用CMS来清理老年代（-XX:+UseConcMarkSweepGC）使用并行回收器清理新生代（-XX:+UseParNewGC）
 * 将堆的12.5%（-Xmn128m）分配给新生代，并将Eden区和Survivor区的大小限制为一样的。
 * -Xms1g -Xmx1g -XX:+UseConcMarkSweepGC -XX:+UseParNewGC -Xmn128m
 * 配置二：
 * 512m的堆（-Xms512m -Xmx512m）
 * 新生代和老年代都使用Parellel GC(-XX:+UseParallelGC)
 * 将堆的75%分配给新生代（-Xmn384）
 * <p/>
 * 第一个配置（大堆，大的老年代，CMS GC）每秒能吞食8.2头猪
 * 第二个配置（小堆，大的新生代，Parellel GC）每秒可以吞食9.2头猪
 * -Xms512m -Xmx512m  -XX:+UseParallelGC -Xmn384m
 * 使用jstat 命令，jstat -gc -t -h10 pid 1s
 * User: shijingui
 * Date: 2016/11/3
 */
public class GCTypeSelectOptimize {
    private static final int MAX_Object = 1000;
    private static volatile List objectList = new ArrayList<>();
    private static volatile int objAddCount = 0;
    private static int bytes = 1 * 1024 * 1024;//1M

    public static void main(String[] args) {
        new Add().start();
        new Clean().start();
    }

    static class Add extends Thread {
        public void run() {
            while (true) {
                objectList.add(new byte[bytes]);
                if (objAddCount > MAX_Object) return;
                SleepUtil.millisecond(100);
            }
        }
    }

    static class Clean extends Thread {
        public void run() {
            long st = System.currentTimeMillis();
            while (true) {
                SleepUtil.millisecond(2000);//休息2秒
                objAddCount += objectList.size();
                objectList = new ArrayList();
                if (objAddCount > MAX_Object) {
                    System.out.format("Clean %d objectList in %d ms.%n", objAddCount, System.currentTimeMillis() - st);
                    return;
                }
            }
        }
    }
}
