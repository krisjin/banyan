package org.banyan.concurrent.assembly;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * -XX:+UnlockDiagnosticVMOptions -XX:+PrintAssembly
 *
 * @author krisjin
 * @date 2020/11/16
 */
public class Test {

    public static void main(String[] args) {


        // 获得系统时间.
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM");
        String nowTime = simpleDateFormat.format(date);
        System.out.println(nowTime);


        Calendar calendar = Calendar.getInstance();
        calendar.add(calendar.MONTH, -1);
        SimpleDateFormat sdf = new SimpleDateFormat("2021-01");
        String dateStr = sdf.format(calendar.getTime());

        System.out.println(dateStr);

        String ss="中国特产·当阳助农馆 ";


        System.out.println(ss.trim());
    }
}
