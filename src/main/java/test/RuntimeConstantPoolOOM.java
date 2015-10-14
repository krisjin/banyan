package test;

import java.util.ArrayList;
import java.util.List;

/**
 * Jdk1.6
 * VM Args: -XX:PermSize=10m -XX:MaxPermSize=10m
 * <p/>
 * User : krisibm@163.com
 * Date: 2015/10/14
 * Time: 23:09
 */
public class RuntimeConstantPoolOOM {

    public static void main(String[] args) {
        //使用List保持着常量池的引用，避免Full GC回收常量池
        List<String> list = new ArrayList<String>();
        //10MB的PermSize在integer范围内足够产生OOM了
        int i = 0;
        while (true) {
            list.add(String.valueOf(i++).intern());
        }
    }
}
