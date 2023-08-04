package org.banyan.concurrent.base.computetask;

import com.alibaba.fastjson.JSONObject;
import org.banyan.concurrent.util.SleepUtil;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

/**
 * 这是什么
 */
public class Client {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Computable computable = new ComputeDefault();
        ComputeWrapper<Integer, Integer> computeWrapper = new ComputeWrapper<Integer, Integer>(computable);
        int val = computeWrapper.compute(11);
        System.out.println("第一次计算值:" + val);
        SleepUtil.second(2);
        val = computeWrapper.compute(11); //第二次从缓存去结果
        System.out.println("第二次计算值:" + val);

        Map<String, Object> m = new HashMap<String, Object>();
        Map<String, Object> m1 = new HashMap<String, Object>();
        Map<String, Object> m2 = new HashMap<String, Object>();

        m.put("chooseRouteValue", m1);
        m1.put("law_verify", m2);
        m2.put("asdf", 1);
        System.err.println(JSONObject.toJSONString(m));
    }
}
