package test;

import java.util.Map;
import java.util.Stack;

/**
 * <p/>
 * User : krisibm@163.com
 * Date: 2015/10/12
 * Time: 9:57
 */

public class TestComm {

    private String name;
    private int age;
    private double money;

    public static void main(String[] args) {
        gc();
        int i = 16 / 16;
        System.out.println(i);
        Map<Integer, String> enumMap = EnumStaticMethod.getEnumMap();
        System.out.println(enumMap.size());
        Stack stack = new Stack();
        stack.add("name");
        stack.add("sex");

        Object object=stack.pop();
        System.out.println(object);

    }


    public static void gc() {
        System.gc();
    }
}
