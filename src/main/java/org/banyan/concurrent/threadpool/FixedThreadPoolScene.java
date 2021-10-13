package org.banyan.concurrent.threadpool;


import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class FixedThreadPoolScene {

    private ExecutorService fixedPool = Executors.newFixedThreadPool(4);

    public static void main(String[] args) {
        FixedThreadPoolScene fixedThreadPoolScene = new FixedThreadPoolScene();
    }


    public void test1() {

        System.out.println("=== FixedThreadPool ===");
        List<Future<UUID>> uuids = new LinkedList<>();

        Map mapParam = new HashMap<>();

        mapParam.put("pageSize", 10);
        mapParam.put("pageNum", 1);

        for (int i = 1; i <= 20; i++) {
            mapParam.put("pageNum", i);
            Future<UUID> submitted = fixedPool.submit(() -> {
                page(mapParam);

                UUID randomUUID = UUID.randomUUID();
                return randomUUID;
            });
            uuids.add(submitted);
        }


    }


    private void page(Map param) {
        Integer pageSize = Integer.valueOf(param.get("pageSize") + "");
        Integer pageNum = Integer.valueOf(param.get("pageNum") + "");
        System.out.println("pageSize=" + pageSize + ",pageNum=" + pageNum);
    }


}
