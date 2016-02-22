package test;

import java.util.Map;

/**
 * <p/>
 * User : krisibm@163.com
 * Date: 2015/10/12
 * Time: 9:57
 */
public class Test {

    public static void main(String[] args) {
        int i = 16 / 16;
        System.out.println(i);
        Map<Integer, String> enumMap = EnumStaticMethod.getEnumMap();
        System.out.println(enumMap.size());
    }
}
