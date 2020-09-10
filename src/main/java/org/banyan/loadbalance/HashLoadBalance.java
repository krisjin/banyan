package org.banyan.loadbalance;

import java.util.ArrayList;
import java.util.List;

/**
 * @author krisjin on 2018/1/6
 */
public class HashLoadBalance {
    static List<String> serverIpList = new ArrayList<>();

    public static void main(String[] args) {
        String userId = "kris";
        for (int i = 0; i < 10; i++) {
            System.out.println(getIp(userId));
        }
    }

    static {
        serverIpList.add("192.168.1.100");
        serverIpList.add("192.168.1.101");
        serverIpList.add("192.168.1.102");
        serverIpList.add("192.168.1.103");
        serverIpList.add("192.168.1.104");
    }

    static String getIp(String remoteIp) {
        int hashCode = remoteIp.hashCode();
        int serverPost = Math.abs(hashCode % serverIpList.size());
        return serverIpList.get(serverPost);
    }

}
