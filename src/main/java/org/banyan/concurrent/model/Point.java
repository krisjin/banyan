package org.banyan.concurrent.model;

/**
 * 不可变的Point类,所以是线程安全的。
 */
public class Point {

    public final int x;
    public final int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

}
