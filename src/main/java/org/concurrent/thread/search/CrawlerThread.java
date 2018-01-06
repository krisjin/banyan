package org.concurrent.thread.search;

import java.io.File;
import java.util.concurrent.BlockingQueue;

/**
 * 爬虫线程
 * <p/>
 * User : krisibm@163.com
 * Date: 2015/4/11
 * Time: 14:10
 */
public class CrawlerThread implements Runnable {

    private final BlockingQueue<File> fileQueue;
    private final File dir;

    public CrawlerThread(BlockingQueue<File> fileQueue, File dir) {
        super();
        this.fileQueue = fileQueue;
        this.dir = dir;
    }


    private void crawl(File root) throws InterruptedException {
        File[] files = root.listFiles();
        if (files != null) {
            for (File f : files) {
                if (f.isDirectory()) {
                    crawl(f);
                } else if (!fileQueue.contains(f)) {
                    System.out.println("Crawl file " + f.getAbsoluteFile());
                    //阻塞式插入
                    fileQueue.put(f);
                }
            }
        }
    }


    @Override
    public void run() {
        try {
            crawl(dir);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
