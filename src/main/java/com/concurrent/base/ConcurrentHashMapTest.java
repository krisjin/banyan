package com.concurrent.base;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * <p/>
 * User : krisibm@163.com
 * Date: 2015/9/15
 * Time: 17:29
 */
public class ConcurrentHashMapTest {


    public static void main(String[] args) {

        final Map<String, String> map = new HashMap<String, String>();

        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10000; i++) {

                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            map.put(UUID.randomUUID().toString(), "");
                        }
                    }, "chThread-" + i).start();
                }

            }
        });

        t.start();
        try {
            t.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
