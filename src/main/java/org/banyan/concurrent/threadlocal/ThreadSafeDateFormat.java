package org.banyan.concurrent.threadlocal;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Thread Local Confinement
 * Using a thread safe SimpleDateFormat object
 */
public class ThreadSafeDateFormat {

    private static final ThreadLocal<SimpleDateFormat> threadLocalDateFormat = new ThreadLocal<SimpleDateFormat>() {
        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat("DD/MM/YYYY HH:mm:ss");
        }
    };

    public String format(Date date) {
        return threadLocalDateFormat.get().format(date);
    }

    public static void main(String[] args) {

    }
}