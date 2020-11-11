package com.gdut.jiyi.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
    private static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    private static final String DATE_TIME_NOT_BLANK_FORMAT = "yyyyMMddHHmmss";

    public static String getDateStr(){
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_TIME_FORMAT);
        return sdf.format(date);
    }

    public static String getDateNotBlankStr(){
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_TIME_NOT_BLANK_FORMAT);
        return sdf.format(date);
    }


}
