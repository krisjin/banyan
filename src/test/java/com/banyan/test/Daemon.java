package com.banyan.test;

import com.alibaba.fastjson.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

/**
 * <p/>
 * User : krisjin
 * Date: 2015/10/16
 * Time: 15:40
 */
public class Daemon {

    public static void main(String[] args) {
        Thread t = new Thread(new DaemonRunner());
        t.setDaemon(true);
        t.setPriority(10);
        t.setName("Daemon thread");
        t.start();


        String s = "27*135";

        s = s.substring(s.lastIndexOf("*"));

        System.err.println(s);


        System.out.println(2 & 2);


        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:MM:ss");

        Date d = new Date();
        d.setTime(1565026201127l);


        System.out.println(sdf.format(d));




        System.out.println("--" + (4 & 124));

        String def = "      \n" +
                "{\"posid\":\"*6055*24777\",\"matid\":\"轮播banner**\",\"pagid\":\"1\",\"resdata\":{\"cre_id\":\"\",\"rs_id\":\"mc-mkt-cms*6055#24777\",\"res_id\":\"565*4121\",\"str_id\":\"\"},\"ordid\":\"*10*0-0\"}";


        Map e = JSONObject.parseObject(def, Map.class);
        Map m = (Map) e.get("resdata");

        System.err.println(m.get("res_id"));
    }

    static class DaemonRunner implements Runnable {

        @Override
        public void run() {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                System.out.println("Daemon thread run.");
            }
        }
    }
}
