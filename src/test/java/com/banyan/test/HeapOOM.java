package com.banyan.test;

import java.util.ArrayList;
import java.util.List;

/**
 * VM Args: -Xms20m -Xmx20m -XX:+HeapDumpOnOutOfMemoryError
 * <p/>
 * User : krisjin
 * Date: 2015/10/14
 * Time: 22:37
 */
public class HeapOOM {
    public static void main(String[] args) {
        List<OOMObject> list = new ArrayList<OOMObject>();

//        while (true) {
//            list.add(new OOMObject());
//        }

    }

    static class OOMObject {
    }
}
