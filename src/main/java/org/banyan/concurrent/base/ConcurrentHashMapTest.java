package org.banyan.concurrent.base;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ForkJoinPool;

public class ConcurrentHashMapTest {

    public static void main(String[] args) {
        System.out.println("Parallelism: " + ForkJoinPool.getCommonPoolParallelism());
//        testForEach();
//        testSearch();
        testReduce();
    }

    private static void testReduce() {
        ConcurrentHashMap<String, String> map = new ConcurrentHashMap<>();
        map.putIfAbsent("k1", "v1");
        map.putIfAbsent("k2", "v2");
        map.putIfAbsent("k3", "v3");
        map.putIfAbsent("k4", "v4");

        String reduced = map.reduce(1, (key, value) -> key + "=" + value, (s1, s2) -> s1 + ", " + s2);
        System.out.println(reduced);
    }

    private static void testSearch() {
        ConcurrentHashMap<String, String> map = new ConcurrentHashMap<>();
        map.putIfAbsent("k1", "v1");
        map.putIfAbsent("k2", "v2");
        map.putIfAbsent("k3", "v3");
        map.putIfAbsent("k4", "v4");

        System.out.println("\nsearch()\n");
        String result1 = map.search(1, (key, value) -> {
            System.out.println(Thread.currentThread().getName());
            if (key.equals("k1") && value.equals("v1")) {
                return "v2";
            }
            return null;
        });

        System.out.println(result1);
        System.out.println("\nsearchValues()\n");
        String result2 = map.searchValues(1, value -> {
            System.out.println(Thread.currentThread().getName());
            if (value.length() > 3) {
                return value;
            }
            return null;
        });
        System.out.println(result2);
    }

    private static void testForEach() {
        ConcurrentHashMap<String, String> map = new ConcurrentHashMap<>();
        map.putIfAbsent("k1", "v1");
        map.putIfAbsent("k2", "v2");
        map.putIfAbsent("k3", "v3");
        map.putIfAbsent("k4", "v4");

        map.forEach(2, (key, value) -> System.out.printf("key: %s; value: %s; thread: %s\n", key, value, Thread.currentThread().getName()));
        System.out.println(map.mappingCount());
    }

}
