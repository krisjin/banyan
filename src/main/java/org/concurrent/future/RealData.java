package org.concurrent.future;

import org.concurrent.util.SleepUtil;

/**
 * User krisjin
 * Date 2017/5/6
 */
public class RealData implements Data {
    protected final String result;

    public RealData(String para) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < 10; i++) {
            sb.append(para);
            //使用sleep，代替一个很慢的操作过程
            SleepUtil.millisecond(100);
        }
        result = sb.toString();
    }

    @Override
    public String getResult() {
        return result;
    }
}
