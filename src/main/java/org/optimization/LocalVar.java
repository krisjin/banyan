package org.optimization;

/**
 * 使用局部变量对好处，调用方法时传递的参数及在调用中创建的临时标亮都保存在栈中，速度较快。
 * 其它变量，如静态变量，实例变量等，都在堆中创建，速度较慢。
 *
 * @author krisjin on 2018/1/6
 */
public class LocalVar {
    static int a;

    public static void main(String[] args) {

        long st = System.currentTimeMillis();
        useGlobalVar();
        System.out.println("static var cost:" + (System.currentTimeMillis() - st) + "ms");
        st = System.currentTimeMillis();
        useLocalVar();
        System.out.println("local var cost:" + (System.currentTimeMillis() - st) + "ms");
    }


    public static void useLocalVar() {
        int b = 0;
        for (int i = 0; i < 100000000; i++) {
            b++;
        }
    }

    public static void useGlobalVar() {
        for (int i = 0; i < 100000000; i++) {
            a++;
        }

    }


}
