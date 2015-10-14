package test;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;

/**
 * <p/>
 * User : krisibm@163.com
 * Date: 2015/10/14
 * Time: 14:20
 */
public class MultiThread {
    public static void main(String[] args) {
        ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
        //不需要获取同步的monitor 和 synchronizer信息，仅获取线程和线程堆栈信息。
        ThreadInfo[] threadInfos = threadMXBean.dumpAllThreads(false, false);
        for (ThreadInfo threadInfo : threadInfos) {
            String tName = threadInfo.getThreadName();
            long tId = threadInfo.getThreadId();
            System.out.println(tName + "，" + tId);
        }
    }
}
