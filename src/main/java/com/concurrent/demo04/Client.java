package com.concurrent.demo04;

import java.io.File;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * <p/>
 * User : krisibm@163.com
 * Date: 2015/4/11
 * Time: 14:26
 */
public class Client {

    public static void main(String[] args) {
        BlockingQueue queue = new LinkedBlockingQueue<File>(1000);
        final int N_CONSUMERS = Runtime.getRuntime().availableProcessors() / 2;
        File root = new File("d:/Downloads");

        new CrawlerThread(queue, root).start();

        for (int i = 0; i < N_CONSUMERS; i++) {
            new IndexThread(queue).start();
        }


    }
}
