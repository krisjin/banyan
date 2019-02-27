package org.loadbalance;

import java.util.ArrayList;
import java.util.List;

/**
 * @author krisjin on 2018/1/6
 */
public class HashLoadBalance {


    static List<String> serverIpList = new ArrayList<>();

    static {
        serverIpList.add("192.168.1.100");
        serverIpList.add("192.168.1.101");
        serverIpList.add("192.168.1.102");
        serverIpList.add("192.168.1.103");
        serverIpList.add("192.168.1.104");
    }

    public static void main(String[] args) {
        String remoteIp = "abc.com";
        for (int i = 0; i < 100; i++) {
            System.out.println(getIp(remoteIp));
        }

    }


    static String getIp(String remoteIp) {
        int hashCode = "dfafd".hashCode();
        int serverPost = hashCode % serverIpList.size();
        return serverIpList.get(serverPost);
    }

}
