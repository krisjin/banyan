package org.reference;

/**
 * 强引用是java引用类型中最高级级别的，这种引用类型不会被jvm回收，即使内存空间不够用了
 * 也不会释放，直接报OOM。
 *
 * @author krisjin on 2018/1/6
 */
public class StrongRef {

    StringBuffer sb = new StringBuffer("Hi");


    public static void main(String[] args) {
        String str = new String();//str引用在栈上分配，对象在堆空间分配


    }

}
