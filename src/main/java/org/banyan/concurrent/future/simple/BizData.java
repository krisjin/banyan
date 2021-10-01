package org.banyan.concurrent.future.simple;

import org.banyan.concurrent.util.SleepUtil;

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
