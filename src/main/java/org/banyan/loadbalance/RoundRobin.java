package org.banyan.loadbalance;

import java.util.*;

/**
 * 吞吐量遇到，随机算法的效果越接近于轮训算法的效果
 *
 * @author krisjin on 2018/1/6
 */
public class RoundRobin {

    static Integer pos = 0;
    private static Map<String, Integer> serverWeightMap = new HashMap<String, Integer>();

    static {
        serverWeightMap.put("192.168.1.101", 1);
        serverWeightMap.put("192.168.1.102", 1);
        serverWeightMap.put("192.168.1.103", 2);
        serverWeightMap.put("192.168.1.104", 2);
        serverWeightMap.put("192.168.1.105", 3);
        serverWeightMap.put("192.168.1.106", 3);

    }

    public static void main(String[] args) {

        for (int i = 0; i < 10; i++) {
            System.out.println(getIp());
        }

    }


    public static String getIp() {
        //重新创建一个map，避免出现由于服务器上线和下线导致的并发问题
        Map<String, Integer> serverMap = new HashMap<String, Integer>();
        serverMap.putAll(serverWeightMap);
        Set<String> ipSet = serverMap.keySet();
        List<String> ipList = new ArrayList<>();

        for (String ip : ipSet) {//加权重计算
            Integer weight = serverWeightMap.get(ip);
            for (int i = 0; i < weight; i++)
                ipList.add(ip);
        }


        String serverIp;
        synchronized (pos) {
            if (pos >= ipList.size()) {
                pos = 0;
            }
            serverIp = ipList.get(pos);
            pos++;
        }


        return serverIp;
    }

}
