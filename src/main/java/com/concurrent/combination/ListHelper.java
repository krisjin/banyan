package com.concurrent.combination;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 客户端加锁机制，通过客户端加锁实现“若没则添加”
 * <p/>
 * User : krisibm@163.com
 * Date: 2015/9/10
 * Time: 21:19
 */
public class ListHelper<E> {

    public List<E> list = Collections.synchronizedList(new ArrayList<E>());

    public boolean putIfAbsent(E e) {
        synchronized (list) {
            boolean absent = !list.contains(e);
            if (absent) {
                list.add(e);
            }
            return absent;
        }
    }
}
