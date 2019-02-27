package org.loadbalance;

import java.util.*;

/**
 * @author krisjin on 2018/1/6
 */
public class RandomLoad {
    static Map<String, Integer> serverWeightMap = new HashMap<String, Integer>();
    static int randomSize = serverWeightMap.size();

    static {
        serverWeightMap.put("192.168.1.100", 1);
        serverWeightMap.put("192.168.1.101", 1);
        serverWeightMap.put("192.168.1.102", 1);
        serverWeightMap.put("192.168.1.103", 1);
        serverWeightMap.put("192.168.1.104", 50);
    }

    public static void main(String[] args) {
        Map<String, Integer> serverCount = new HashMap<>();

        for (int i = 1; i < 10000; i++) {
            String ip = getIp();
            Integer count = serverCount.get(ip);
            if (count == null) {
                serverCount.put(ip, 1);
            } else {
                serverCount.put(ip, count + 1);
            }
        }

        for (Map.Entry entry : serverCount.entrySet())
            System.out.println(entry.getKey() + "=" + entry.getValue());

    }

    public static String getIp() {

        Map<String, Integer> serverMap = new HashMap<>();
        serverMap.putAll(serverWeightMap);

        Set<String> ipSet = serverMap.keySet();
        List<String> ipList = new ArrayList<>();

        for (String ip : ipSet) {//加权重
            Integer weight = serverWeightMap.get(ip);

            for (int i = 0; i < weight; i++) {
                ipList.add(ip);
            }
        }

        Random random = new Random();
        Integer index = random.nextInt(ipList.size());
        String serverIp = ipList.get(index);
        return serverIp;
    }
}
