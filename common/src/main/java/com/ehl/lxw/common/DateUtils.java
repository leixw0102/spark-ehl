package com.ehl.lxw.common;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

/**
 * Created by 雷晓武 on 2017/8/21.
 */
public class DateUtils {

    public static String getType(long time){
        LocalDateTime localDateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(time), ZoneId.systemDefault());
        System.out.println(localDateTime.toString());
        localDateTime.getHour();
        return null;
    }

    public static void main(String[] args) {
        getType(System.currentTimeMillis());
    }

}
