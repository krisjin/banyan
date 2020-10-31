package org.banyan.concurrent.producerconsumer;

import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * @author krisjin
 * @date 2020/10/29
 */
public class MessageQueue {

    private static BlockingDeque<Object> queue = new LinkedBlockingDeque<>();

    public static BlockingDeque<Object> get() {
        return queue;
    }

}
