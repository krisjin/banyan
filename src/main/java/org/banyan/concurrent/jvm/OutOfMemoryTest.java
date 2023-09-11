package org.banyan.concurrent.jvm;

import java.util.HashMap;
import java.util.Map;

/**
 * -Xms20m -Xmx20m -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=/usr/local/tool/training/log/oom.hprof
 */
public class OutOfMemoryTest {
    public static final int _1MB = 1024 * 1024;

    public static void main(String[] args) {
        byte[] b1 = new byte[4 * _1MB];
        Map<Integer, byte[]> map = new HashMap<Integer, byte[]>();
        int count = 1;
        while (true) {
            map.put(count, b1);
            count++;
        }
    }


}
