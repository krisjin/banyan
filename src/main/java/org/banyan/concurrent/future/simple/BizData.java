package org.banyan.concurrent.future.simple;

import org.banyan.concurrent.util.SleepUtil;

/**
 * User krisjin
 * Date 2017/5/6
 */
public class BizData {
    private final String result;

    public BizData(String para) {
        SleepUtil.second(3); //使用sleep，代替一个很慢的操作过程
        result = "hi," + para;
    }

    public String getResult() {
        return result;
    }
}
