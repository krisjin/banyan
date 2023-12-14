package org.banyan.patterns.thread_safe;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @author kris
 * @date 2023/11/30
 */
public class DateUtil {

    public static void main(String[] args) {

        LocalDate date = LocalDate.of(2023, 11, 29);

        LocalDate date3 = null;
        LocalDateTime date1 = LocalDateTime.of(2023, 11, 30, 23, 55, 0);

        System.err.println(11111);

        if (date.isBefore(date1.toLocalDate()) ) {
            System.err.println(11);
        }

    }
}
