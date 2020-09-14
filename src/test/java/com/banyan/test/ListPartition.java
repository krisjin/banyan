package com.banyan.test;

import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.List;

/**
 * User:krisjin
 * Date:2020-07-22
 */
public class ListPartition {

    public static void main(String[] args) {
        List<String> stringList = new ArrayList<>();

        for (int i = 0; i < 10000; i++) {
            stringList.add("s" + i);
        }
        List<List<String>> listPartition = Lists.partition(stringList, 20);

        for (List<String> l : listPartition) {
            l.clear();
            new ArrayList<>(l).clear();
//            listPartition = null;

        }
    }
}
