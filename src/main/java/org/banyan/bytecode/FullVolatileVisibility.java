package org.banyan.bytecode;

/**
 * User:krisjin
 * Date:2020-05-01
 */
public class FullVolatileVisibility {

    private String varA;
    private String varB;
    private volatile String varC;//声明volatile

    public void readVar() {
        String strVar = this.varC;//我在开始
        strVar += varA;
        strVar += varB;
    }

    public void updateVar(String varA, String varB, String varC) {
        this.varA = varA;
        this.varB = varB;
        this.varC = varC;//我在最后
    }

}
