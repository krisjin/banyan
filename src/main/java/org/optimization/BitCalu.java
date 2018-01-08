package org.optimization;

/**
 * @author shijingui on 2018/1/8
 */
public class BitCalu {

    public static void main(String[] args) {

        //按位与(&)就是将数据的每一位进行与运算，与运算的原则为：同真则为真，其它全为假（1为真，0为假）。
        int a = 1 & 2;
        System.out.println(a);

        //按位或 (|)就是将数据的每一位进行或运算，或运算的原则为：同假则为假，其它全为真（1为真，0为假）。
        int b = 1 | 2;
        System.out.println(b);

        //带符号右移(>>)就是将数据的每一位进行右移，超出舍弃，空位补0。相当于原数据除以2。
        int c = 6 >> 1;
        System.out.println(c);


    }
}
