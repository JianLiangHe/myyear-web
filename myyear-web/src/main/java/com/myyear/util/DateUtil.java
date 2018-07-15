package com.myyear.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DateUtil {

	private static final long MILLISECONDS_PER_DAY = 24L * 3600 * 1000;

	private static Logger log = LoggerFactory.getLogger(DateUtil.class);

	public static String getUploadPath() {
		return new SimpleDateFormat("yyyyMMdd").format(new Date()) + "/";
	}

	/**
	 * 格式化当前时间
	 * 
	 * @param format
	 * @return
	 */
	public static String now(String format) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(new Date());
	}

	/**
	 * 格式化时间
	 * 
	 * @param date
	 * @param format
	 * @return
	 */
	public static String format(Date date, String format) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(date);
	}

	/**
	 * 根据String("yyyy-MM-dd HH:mm:ss")时间转化成时间戳
	 * 
	 * @param String
	 *            strTime
	 * @return Long
	 */
	public static long getLongTime(String strTime) {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		long time = 0L;
		try {
			Date d = df.parse(strTime);
			time = d.getTime();
		} catch (ParseException e) {
			System.out.println("String时间转换Long 出错");
			e.printStackTrace();
		}
		return time;
	}

	/**
	 * 根据String("yyyy-MM-dd")时间转化成时间戳
	 * 
	 * @param String
	 *            strTime
	 * @return Long
	 */
	public static long getLongTime_short(String strTime) {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		long time = 0L;
		try {
			Date d = df.parse(strTime);
			time = d.getTime();
		} catch (ParseException e) {
			System.out.println("String时间转换Long 出错");
			e.printStackTrace();
		}
		return time;
	}

	/**
	 * 时间戳转换成String日期
	 * 
	 * @param Long
	 *            time
	 * @return String
	 */
	public static String getStringTime(long time) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(time);
	}

	/**
	 * 获取结束时间("yyyy-MM-dd")
	 * 
	 * @return long
	 */
	public static long getEndTime(String week, String statrTime, int classNum) {
		long endtime = 0L;

		if (week == null) {
			week = "六,日";
		}

		try {
			String[] weeks = week.split(",");
			long stime = getLongTime_short(statrTime);
			endtime = MILLISECONDS_PER_DAY * (classNum / weeks.length);
			if (classNum % weeks.length != 0) {
				endtime += classNum % weeks.length * MILLISECONDS_PER_DAY;
			}
			endtime += stime;
		} catch (Exception e) {
			System.out.println("获取结束时间 出错......" + e.getMessage());
		}

		return endtime;
	}

	/**
	 * 获取今日之前时间
	 * 
	 * @param month
	 *            例 : -1 之前一个月, -2 之前两个月
	 * @return yyyy-MM-dd HH:mm:ss
	 */
	public static String getTimeMonthAgo(int month) {
		Date date = new Date();
		Calendar cl = Calendar.getInstance();
		cl.setTime(date);
		cl.add(Calendar.MONTH, month);
		SimpleDateFormat dd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String time = dd.format(cl.getTime());
		return time;
	}

	/**
	 * 字符串转Date类型
	 * 
	 * @param time
	 * @return yyyy-MM-dd HH:mm:ss
	 */
	public static Date stringToDate(String time) {
		Date date = null;
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			date = df.parse(time);
		} catch (ParseException e) {
			e.printStackTrace();
			log.info(DateUtil.class.getName() + "---" + "字符串转Date类型出错");
		}
		return date;
	}

	/**
	 * 字符串转Date类型
	 * 
	 * @param time
	 * @return yyyy-MM-dd
	 */
	public static Date stringToDate2(String time) {
		if(time == null || time.trim().length() <= 0){
			return null;
		}
		Date date = null;
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		try {
			date = df.parse(time);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(DateUtil.class.getName() + "---" + "字符串转Date类型出错");
		}
		return date;
	}
	
	/**
	 * 字符串转Date类型
	 * 
	 * @param time
	 * @return yyyyMMddHHmmss
	 */
	public static Date stringToDate3(String time) {
		if(time == null || time.trim().length() <= 0){
			return null;
		}
		Date date = null;
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
		try {
			date = df.parse(time);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(DateUtil.class.getName() + "---" + "字符串转Date类型出错");
		}
		return date;
	}

	/**
	 * 计算两个日期之间相差的天数
	 * 
	 * @param smdate
	 *            较小的时间
	 * @param bdate
	 *            较大的时间
	 * @return 相差天数
	 * @throws ParseException
	 */
	public static int daysBetween(Date smdate, Date bdate) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		smdate = sdf.parse(sdf.format(smdate));
		bdate = sdf.parse(sdf.format(bdate));
		Calendar cal = Calendar.getInstance();
		cal.setTime(smdate);
		long time1 = cal.getTimeInMillis();
		cal.setTime(bdate);
		long time2 = cal.getTimeInMillis();
		long between_days = (time2 - time1) / (1000 * 3600 * 24);

		return Integer.parseInt(String.valueOf(between_days));
	}

	/**
	 * 比较两个时间相差的分钟数
	 * 
	 * @param time1
	 * @param time2
	 * @return
	 */
	public static long minutesBetween(long time1, long time2) {
		long min = 0;
		long diff;
		if (time1 < time2) {
			diff = time2 - time1;
		} else {
			diff = time1 - time2;
		}
		min = diff / (60 * 1000);
		return min;
	}

	/**
	 * 两个时间相差距离多少天多少小时多少分多少秒 @Title: 两个时间相差距离多少天多少小时多少分多少秒 @Description:
	 * 两个时间相差距离多少天多少小时多少分多少秒 @param str1 时间参数 1 格式：1990-01-01 12:00:00 @param
	 * str2 时间参数 2 格式：2009-01-01 12:00:00 @return Map<String,Long>
	 * key=day,hour,min,sec @throws
	 */
	public static Map<String, Long> getDistanceTimes(String str1, String str2) {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date one;
		Date two;
		long day = 0;
		long hour = 0;
		long min = 0;
		long sec = 0;
		try {
			one = df.parse(str1);
			two = df.parse(str2);
			long time1 = one.getTime();
			long time2 = two.getTime();
			long diff;
			if (time1 < time2) {
				diff = time2 - time1;
			} else {
				diff = time1 - time2;
			}
			day = diff / (24 * 60 * 60 * 1000);
			hour = (diff / (60 * 60 * 1000) - day * 24);
			min = ((diff / (60 * 1000)) - day * 24 * 60 - hour * 60);
			sec = (long) ((diff / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60) + 0.5);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Map<String, Long> map = new HashMap<String, Long>();
		map.put("day", day);
		map.put("hour", hour);
		map.put("min", min);
		map.put("sec", sec);
		return map;
	}

	/**
	 * 两个时间相差距离多少秒 @Title: 两个时间相差距离多少秒 @Description: 两个时间相差距离多少秒 @param str1
	 * 时间参数 1 格式：1990-01-01 12:00:00 @param str2 时间参数 2 格式：2009-01-01
	 * 12:00:00 @return Long @throws
	 */
	public static Long getDistanceSec(String str1, String str2) {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date one;
		Date two;
		long sec = 0;
		try {
			one = df.parse(str1);
			two = df.parse(str2);
			long time1 = one.getTime();
			long time2 = two.getTime();
			if (time1 < time2) {
				sec = (time2 - time1) / 1000;
			} else {
				sec = (time1 - time2) / 1000;
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return sec;
	}

	/**
	 * 获取星期
	 * 
	 * @param dt
	 * @return String
	 */
	public static String getWeekOfDate(Date dt) {
		String[] weekDays = { "星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六" };
		Calendar cal = Calendar.getInstance();
		cal.setTime(dt);
		int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
		if (w < 0)
			w = 0;
		return weekDays[w];
	}

	/**
	 * 获取时间 日期
	 * 
	 * @param time
	 * @return String
	 */
	public static String getdateOftime(long time) {
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm MM/dd");
		return sdf.format(time);
	}

	/**
	 * 根据日期获取年龄
	 * 
	 * @param day
	 * @return long
	 */
	public static long getbirthday(long day) {
		long today = System.currentTimeMillis();
		long birthday = (today - day) / (365 * MILLISECONDS_PER_DAY);
		return birthday;
	}

	/*
	 * 当前时间+8小时
	 * 
	 */
	public static String formatTimeEight(String time) throws Exception {
		Date d = null;
		SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		d = sd.parse(time);
		long rightTime = (long) (d.getTime() + 8 * 60 * 60 * 1000); // 把当前得到的时间用date.getTime()的方法写成时间戳的形式，再加上8小时对应的毫秒数
		String newtime = sd.format(rightTime);// 把得到的新的时间戳再次格式化成时间的格式
		return newtime;
	}

	/**
	 * 根据生日获得年龄,以年份为进位，不算月和日
	 * @param birthDay 生日
	 * @return 年龄
	 * @throws Exception 出生日期超过今天
	 */
	public static int getAge(Date birthDay) throws Exception {
		Calendar cal = Calendar.getInstance();

		if (cal.before(birthDay)) {
			throw new IllegalArgumentException("出生日期超出今天");
		}
		int yearNow = cal.get(Calendar.YEAR);
		
		cal.setTime(birthDay);
		int yearBirth = cal.get(Calendar.YEAR);

		int age = yearNow - yearBirth;
		return age;
	}
	
	
	/**
	 * 获取今天之前或者之后的日期(yyyy-MM-dd)
	 * @param day -1:昨天  1:明天
	 * @return 
	 */
	public static String getBeforeOrAfterDate(int day){
		SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DATE, day);
		return sDateFormat.format(calendar.getTime());
	}
	
}
