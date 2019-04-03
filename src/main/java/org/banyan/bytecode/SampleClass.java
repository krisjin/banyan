package org.banyan.bytecode;

/**
 * User:shijingui
 * Date:2019/3/20
 *  
 */
public class SampleClass {

    private String member;

    private static String staticMember;


    public static void main(String[] args) {
        Integer i1 = new Integer(123);
        Integer i2 = new Integer(123);
        System.out.println(i1 == i2);

        Long i5 = new Long(128);
        Long i51 = new Long(128);
        Long i52 = new Long(456);
        Long i3 = Long.valueOf(128);
        Long i4 = Long.valueOf(128);

        System.out.println(i3 == i4);

    }
}
