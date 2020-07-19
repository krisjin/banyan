package org.banyan.base;

/**
 * User:krisjin
 * Date:2020-07-19
 */
public class StringHelper {

    public static void main(String[] args) {


        internTest();

    }


    public static void internTest() {

        String strs = "what is now ?";

        String[] newStrs = strs.split(" ");

        String aaa = newStrs[2].intern();

        String s1 = "now";
        String s2 = "now";

        System.err.println(s1 == newStrs[2]);
    }


    public static void iterningEmpty() {

        String s = "this is string";

        String s1="";





    }
}
