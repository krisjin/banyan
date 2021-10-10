package org.banyan.concurrent.threadpool;


import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class FixedThreadPoolScene {

    private ExecutorService fixedPool = Executors.newFixedThreadPool(4);

    public static void main(String[] args) {
        FixedThreadPoolScene fixedThreadPoolScene = new FixedThreadPoolScene();

        fixedThreadPoolScene.test1();
//        fixedThreadPoolScene.test2();


        List<String> l = new ArrayList<>();
        l.add("d");
        System.err.println(l);

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

//        System.out.println("" + System.currentTimeMillis());

//        fixedPool.execute(() -> uuids.forEach((f) -> {
//            try {
//                System.out.println("Result " + f.get() + " from " + Thread.currentThread().getName());
//            } catch (InterruptedException | ExecutionException e) {
//                e.printStackTrace();
//            }
//        }));
//
//        System.out.println("\n\n");
    }


    private void page(Map param) {

        Integer pageSize = Integer.valueOf(param.get("pageSize") + "");
        Integer pageNum = Integer.valueOf(param.get("pageNum") + "");


        System.out.println("pageSize=" + pageSize + ",pageNum=" + pageNum);


    }


    public void test2() {
        System.out.println("222");
    }

}
