package com.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 时间工具类
 * @author yunfeng.cheng
 * @create 2016-07-24
 */
public class DateUtils {
	
	public static String getDateStr(String pattern){
		return getDateStr(0L, pattern);
	}
	
	public static String getDateStr(long time, String pattern){
        Date date = new Date();
        if (time > 0) {
            date.setTime(time);
        }
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        return format.format(date);
	}
	
}
