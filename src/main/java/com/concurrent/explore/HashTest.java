package com.concurrent.explore;

import java.util.HashMap;
import java.util.Map;

/**
 * <p/>
 * User : krisibm@163.com
 * Date: 2015/7/14
 * Time: 14:33
 */
public class HashTest {

    public static void main(String[] args) {

        Map<String, String> user = new HashMap<String, String>();

        user.put("name", "krisjin");
        user.put("age", "29");
        user.put("address", "chaoyang");

        String name = user.get("name");


        int hash=322&2;
        System.out.println(hash);

    }

}
