package org.banyan.feature;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @User shijingui
 * @date 2020/9/11
 */
public class A {

    public static int sayNumber(int n) {
        LinkedHashMap<Integer, Integer> map = new LinkedHashMap(n);
        for (int i = 1; i <= n; i++) {
            if (i % 3 == 0) {
                map.put(i, 1);
            } else {
                map.put(i, 0);
            }
        }
        int result = 0;

        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (entry.getValue() == 1) {
                result = entry.getKey();
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int n = sayNumber(20);
        System.err.println(n);
    }
}
