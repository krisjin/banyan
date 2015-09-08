package com.concurrent.demo04;

import java.io.File;
import java.util.concurrent.BlockingQueue;

/**
 * <p/>
 * User : krisibm@163.com
 * Date: 2015/4/11
 * Time: 14:10
 */
public class CrawlerThread extends Thread {

    private final BlockingQueue<File> fileQueue;
    private final File root;

    public CrawlerThread(BlockingQueue<File> fileQueue, File root) {
        super();
        this.fileQueue = fileQueue;
        this.root = root;
    }


    private void crawl(File root) throws InterruptedException {

        File[] files = root.listFiles();
        if (files != null) {

            for (File f : files) {
                if (f.isDirectory()) {
                    crawl(f);
                } else if (!fileQueue.contains(f)) {
                    System.out.println("Crawl file " + f.getAbsoluteFile());
                    fileQueue.offer(f);
                }
            }
        }
    }


    @Override
    public void run() {
        try {
            crawl(root);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
