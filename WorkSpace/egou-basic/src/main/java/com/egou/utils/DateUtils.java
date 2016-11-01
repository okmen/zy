package com.egou.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.SimpleTimeZone;


/**
 * 
 * @ClassName: DateUtils
 * @Description: 描述：日期处理常用类
 * @author xiehz
 * @date 2015�?5�?22�? 上午9:55:21
 *
 */
public class DateUtils {

	public static final String YYYYMMDD = "yyyy-MM-dd";
	public static final String YYYYMMDDHHMMSS = "yyyy-MM-dd HH:mm:ss";
	public static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	public static SimpleDateFormat dateFormatDB = new SimpleDateFormat("yyyyMMdd");// 数据库使用的日期格式
	public static SimpleDateFormat dataTimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	public static String formatDateTime(Date date) {
		return dataTimeFormat.format(date);
	}

	
}
