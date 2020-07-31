package org.banyan.base;

/**
 * User:krisjin
 * Date:2020-07-19
 */
public class StringHelper {

    public static void main(String[] args) {


        internTest();
//        iterningEmpty();

    }


    public static void internTest() {

        String strs = "what is now ?";

        String[] newStrs = strs.split(" ");

//        String aaa = newStrs[2].intern();

        String s1 = "now";
        String s2 = "now";

        System.err.println(s1 == newStrs[2]);
    }


    public static void iterningEmpty() {

        String s = "this is string";

        String emptyStr = s.substring(0, 0);

        String emptyStr2 = emptyStr.length() == 0 ? "" : emptyStr;

        String e4 = "this";
        String e5 = e4.intern();
        String e3 = s.substring(0, 4);


        System.err.println(emptyStr == "");
        System.err.println(emptyStr2 == "");

        System.err.println(e3 == e5);


    }
}
