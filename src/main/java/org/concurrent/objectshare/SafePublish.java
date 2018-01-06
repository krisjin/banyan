package org.concurrent.objectshare;

/**
 * 1.不要在构造函数中使用this应用逸出
 * 2.使用工程方法来防止this引用在构造函数中逸出
 * <p/>
 * User : krisibm@163.com
 * Date: 2015/9/8
 * Time: 22:29
 */
public class SafePublish {

    private final Publish publish;

    private SafePublish() {
        publish = new Publish();
    }

    public static SafePublish newInstance() {
        SafePublish safePublish = new SafePublish();
        return safePublish;
    }

}


class Publish {

}
