package org.banyan.concurrent.jvm;

/**
 * -Xms22528m  -Xmx22528m  -XX:MaxMetaspaceSize=256m -XX:MetaspaceSize=256m  -XX:MaxDirectMemorySize=2048m  -Xss512K -Djava.library.path=/usr/local/lib -server -XX:+UseG1GC -XX:MaxGCPauseMillis=200 -verbose:gc -XX:+PrintGCDetails  -XX:+PrintGCDateStamps -XX:+PrintGCApplicationConcurrentTime -XX:+PrintHeapAtGC -Xloggc:/export/log/gc.log -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=/tmp/Logs -Djava.awt.headless=true -Dsun.net.client.defaultConnectTimeout=60000
 * -Xms20m -Xmx20m -verbose:gc -XX:+PrintGCDetails -XX:+PrintGCDateStamps -XX:+PrintGCTimeStamps -XX:+UseConcMarkSweepGC -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=/usr/local/tool/training/log/cms.log
 * -XX:+CMSScavengeBeforeRemark
 */
public class CmsGcLogTest {

    public static final int _1MB = 1024 * 1024;

    public static void main(String[] args) throws InterruptedException {
        int _1MB = 1024 * 1024;
        byte[] b1 = new byte[4 * _1MB];
        byte[] b2 = new byte[4 * _1MB];
        byte[] b3 = new byte[4 * _1MB];
        byte[] b4 = new byte[4 * _1MB];

//        Thread.sleep(2000);
        b4 = null;
//        byte[] b5 = new byte[4 * _1MB];
//        byte[] b6 = new byte[4 * _1MB];
    }
}
