package test.domain;

import java.io.File;

/**
 * User:krisjin
 * Date:2020-04-14
 */
public class T {
    private int i = 0;
    private int j = 0;
    private volatile boolean k = false;

    public void test() {
        i = 1;
        j = 2;
        k = true;
    }


    public static void main(String[] args) {
        if (new File("/usr/local/gitrep/banyan/src/main/java/test/domain/Addrss.java").exists()){
            System.out.println(1);
        }

    }
}
