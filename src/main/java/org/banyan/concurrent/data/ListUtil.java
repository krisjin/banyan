package org.banyan.concurrent.data;

import com.google.common.collect.Lists;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author krisjin on 2021/8/24
 */
public class ListUtil {


    public static void main(String[] args) {
        List<List<UserData>> userList = Lists.partition(getUserData(), 2);

        System.out.println(userList.size());

        String dt = "2021-09-17";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        try {
            Date d = sdf.parse(dt);
            System.err.println(d);
        } catch (ParseException e) {
            e.printStackTrace();
        }


    }


    public static List<UserData> getUserData() {
        List<UserData> userDatas = new ArrayList<>();

        userDatas.add(new UserData());
        userDatas.add(new UserData());
        userDatas.add(new UserData());
        userDatas.add(new UserData());
        userDatas.add(new UserData());

        return userDatas;

    }

    public static class UserData {

    }

}
