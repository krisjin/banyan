package test.domain;

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


}
