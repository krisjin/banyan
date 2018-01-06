package org.concurrent.model;

/**
 * 不可变的Point类,所以是线程安全的。
 * <p/>
 * User : krisibm@163.com
 * Date: 2015/9/9
 * Time: 22:42
 */
public class Point {

    public final int x;

    public final int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

}
