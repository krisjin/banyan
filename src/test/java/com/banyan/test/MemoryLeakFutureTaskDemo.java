package com.banyan.test;

import com.banyan.test.domain.User;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;
import java.util.Vector;


/**
 * 如何判定内存泄露：
 * 这些对象是可达的，即在有向图中，存在通路可以与其相连；其次，这些对象是无用的，即程序以后不会再使用这些对象。
 * 如果对象满足这两个条件，这些对象就可以判定为Java中的内存泄漏，这些对象不会被GC所回收，然而它却占用内存。
 * User: krisjin
 * Date: 2016/10/26
 */
public class MemoryLeakFutureTaskDemo {

    /**
     * 在循环里申请创建对象，将创建好的对象放到Vector中，然后将对象的引用设置为null,释放引用。
     * 如果只是释放引用本身，Vector仍然对该对象有引用，所以垃圾回收并不会回收。
     * 简单的释放所有的对象，将Vector对象引用设置null;
     * 这样以来存在的对象就处于不可达状态。垃圾回收执行时就会回收这部分内存。
     */
    @Test
    public void staticCollection() {
        Vector vector = new Vector(10);
        for (int i = 0; i < 1000000; i++) {
            Object object = new Object();
            vector.add(object);
            object = null;
        }
    }

    @Test
    public void newInstance() {
        for (; ; ) {
            Object object = new Object();
            object = null;
        }
    }


    /**
     * 使用集合时，存储的对象实现了hashcode。对已添加的集合对象属性进行修改，会出现相同的记录
     */
    @Test
    public void updatePropertyValue() {
        Set<User> userSet = new HashSet<User>();
        User user1 = new User("a", "b", "c");
        User user2 = new User("a2", "b2", "c2");
        User user3 = new User("a3", "b3", "c3");
        userSet.add(user1);
        userSet.add(user2);
        userSet.add(user3);
        System.out.println(userSet.size());
        user1.setUsername("ccc");
        userSet.remove(user1);
        userSet.add(user1);
        System.out.println(userSet.size());


    }
}
