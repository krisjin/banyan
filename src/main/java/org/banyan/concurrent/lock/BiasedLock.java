package org.banyan.concurrent.lock;

import java.util.List;
import java.util.Vector;

/**
 * -XX:+UseBiasedLocking -XX:BiasedLockingStartupDelay=0
 * -XX:-UseBiasedLocking
 * <pre>
 *      大部分情况是没有竞争的，所以可以通过偏向来提高性能
 *      所谓的偏向，就是偏心，即锁会偏向于当前已经占有锁的线程
 *      将对象头Mark的标记设置为偏向，并将线程ID写入对象头Mark
 *      只要没有竞争，获得偏向锁的线程，在将来进入同步块，不需要做同步
 *      当其他线程请求相同的锁时，偏向模式结束
 *      -XX:+UseBiasedLocking
 *      默认启用
 *      在竞争激烈的场合，偏向锁会增加系统负担
 * </pre>
 *
 * @author krisjin on 2019/4/26
 */
public class BiasedLock {
    public static List<Integer> numberList = new Vector<>();

    public static void main(String[] args) throws InterruptedException {
        long begin = System.currentTimeMillis();
        int count = 0;
        int startNum = 0;
        while (count < 10000000) {
            numberList.add(startNum);
            startNum += 2;
            count++;
        }
        long end = System.currentTimeMillis();
        System.out.println(end - begin);
    }


}
