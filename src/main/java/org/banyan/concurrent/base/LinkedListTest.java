package org.banyan.concurrent.base;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author krisjin
 * @date 2020/11/14
 */
public class LinkedListTest {

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        IntStream.range(1, 100).forEach(i -> list.add(i));
        BlockingQueue<Long> blockingQueue = new LinkedBlockingQueue<>(10000);

        List<Long> longList = list.stream().parallel()
                .map(i -> i.longValue())
                .sorted()
                .collect(Collectors.toList());

        System.out.println("blockingQueue" + blockingQueue.toString());
    }
}
