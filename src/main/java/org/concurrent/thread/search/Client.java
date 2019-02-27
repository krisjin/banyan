package org.concurrent.thread.search;

import java.io.File;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * <p/>
 * User : krisjin
 * Date: 2015/4/11
 * Time: 14:26
 */
public class Client {

    public static void main(String[] args) {

        //定义一个阻塞队列
        BlockingQueue queue = new LinkedBlockingQueue<File>();

        final int processor = Runtime.getRuntime().availableProcessors() / 2;
        File dir = new File("d:/download");

        Thread crawlThread = new Thread(new CrawlerThread(queue, dir));
        crawlThread.start();

        for (int i = 0; i < processor; i++) {
            new Thread(new IndexThread(queue)).start();
        }
    }
}
