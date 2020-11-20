package org.banyan.concurrent.base;

import sun.misc.Unsafe;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Field;

/**
 * @author krisjin
 * @date 2020/11/20
 */
public class UnsafeDemo {

    public static void main(String[] args) throws Exception {

        //new关键字创建
        Num num1 = new Num();
        System.out.println("new关键字创建: n=" + num1.getN());

        //反射创建
        Num num2 = Num.class.newInstance();
        System.out.println("反射创建: n=" + num2.getN());


        //Unsafe创建
        Unsafe unsafe = getUnsafe();
        Num num3 = (Num) unsafe.allocateInstance(Num.class);
        System.out.println("Unsafe创建: n=" + num3.getN());

        //使用Unsafe修改内存数据
        Field nField = num3.getClass().getDeclaredField("n");
        unsafe.putInt(num3, unsafe.objectFieldOffset(nField), 2);

        System.out.println("Unsafe 修改后的值: n=" + num3.getN());

        //使用Unsafe加载类

        byte[] bytes = getClazzContent();
        Class numClazz = unsafe.defineClass(null, bytes, 0, bytes.length, null, null);
        Object result = numClazz.getMethod("getN").invoke(numClazz.newInstance());

        System.out.println("Unsafe 加载Num.class文件: n=" + result);

    }


    public static Unsafe getUnsafe() {
        Field f;
        Unsafe unsafe;
        try {
            f = Unsafe.class.getDeclaredField("theUnsafe");
            f.setAccessible(true);
            unsafe = (Unsafe) f.get(null);
        } catch (Exception e) {
            throw new RuntimeException("initial the unsafe failure...");
        }
        return unsafe;
    }

    public static byte[] getClazzContent() throws IOException {
        File f = new File("/usr/local/gitrep/banyan/target/classes/org/banyan/concurrent/base/UnsafeDemo$Num.class");
        try (FileInputStream fis = new FileInputStream(f)) {
            byte[] bytes = new byte[fis.available()];
            fis.read(bytes);
            return bytes;
        }
    }


    public static class Num {
        private int n;

        public Num() {
            this.n = 1;
        }

        public int getN() {
            return n;
        }

        public void setN(int n) {
            this.n = n;
        }
    }

}
