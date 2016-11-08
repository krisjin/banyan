package test;

import javax.management.ObjectName;
import java.lang.management.GarbageCollectorMXBean;
import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;
import java.util.List;

/**
 * <p/>
 * User : krisibm@163.com
 * Date: 2015/10/14
 * Time: 14:20
 */
public class ManagementFactoryTest {
    public static void main(String[] args) {
        ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
        List<GarbageCollectorMXBean> garbageCollectorMXBeanList = ManagementFactory.getGarbageCollectorMXBeans();

        for (GarbageCollectorMXBean gcMXBean : garbageCollectorMXBeanList) {
            String gcName = gcMXBean.getName();
            String memoryPoolNames[] = gcMXBean.getMemoryPoolNames();
            for (String memoryPoolName : memoryPoolNames) {
                System.out.println(memoryPoolName);
            }
            ObjectName objectName = gcMXBean.getObjectName();
            String domainName = objectName.getDomain();
            System.out.println(domainName+"__"+objectName.getCanonicalName());
            System.out.println(gcName);

        }

        //不需要获取同步的monitor 和 synchronize信息，仅获取线程和线程堆栈信息。
        ThreadInfo[] allThreads = threadMXBean.dumpAllThreads(true, true);
        for (ThreadInfo threadInfo : allThreads) {
            String threadName = threadInfo.getThreadName();
            long threadId = threadInfo.getThreadId();
            System.out.println(threadName + "，" + threadId + "," + threadInfo.getLockOwnerName());
        }
    }
}
