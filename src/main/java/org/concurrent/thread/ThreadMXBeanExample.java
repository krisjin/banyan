package org.concurrent.thread;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;

/**
 * User: krisjin
 * Date: 2016/2/3
 */
public class ThreadMXBeanExample {

    public static void main(String... args) {
        ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
        ThreadInfo[] threadInfos = threadMXBean.dumpAllThreads(true, true);
        for (ThreadInfo threadInfo : threadInfos) {
            long threadId = threadInfo.getThreadId();
            String threadName = threadInfo.getThreadName();
            Thread.State state = threadInfo.getThreadState();
            System.out.println(threadInfo.toString());
//            System.out.println("Thread id:" + threadId + " ;Thread name:" + threadName + "; Thread state:" + state.name());
        }
    }
}
