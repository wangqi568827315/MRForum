package com.chinasoft.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.regex.Pattern;


/**
 * 这是我个人编写的对日期时间进行基本操作的类
 * @author 王琦
 * @version V1.60及以上
 * */

public class DateTime {
	static Calendar myCalendar = null;

	
	//获得规定格式的日期
	public static String getDate(){
		myCalendar = Calendar.getInstance();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy年MM月dd日");
		return dateFormat.format(myCalendar.getTime());
	}
	
	//可以自定义日期分隔符的方法
	public static String getDate(String spStr){
		myCalendar = Calendar.getInstance();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy"+spStr+"MM"+spStr+"dd");
		return dateFormat.format(myCalendar.getTime());
	}
	
	//获得星期
	public static String getWeek(){
		myCalendar = Calendar.getInstance();
		int date_week = myCalendar.get(Calendar.DAY_OF_WEEK)-1;
		String str = new String();
		switch (date_week) {
		case 1: str = "星期一";
			break;
		case 2: str = "星期二";
			break;
		case 3: str = "星期三";
			break;
		case 4: str = "星期四";
			break;
		case 5: str = "星期五";
			break;
		case 6: str = "星期六";
			break;
		case 7: str = "星期日";
			break;
		}
		return str;
	}
	
	//获得规定格式的时间
	public static String getTime(){
		myCalendar = Calendar.getInstance();
		SimpleDateFormat dateFormat = new SimpleDateFormat("kk时mm分ss秒");
		return dateFormat.format(myCalendar.getTime());
	}
	//获得规定格式的精确时间(到毫秒)
	public static String getAccTime(){
		myCalendar = Calendar.getInstance();
		SimpleDateFormat dateFormat = new SimpleDateFormat("kk时mm分ss秒SS毫秒");
		return dateFormat.format(myCalendar.getTime());
	}
	
	//可以自定义分隔符的时间
	public static String getTime(String spStr){
		myCalendar = Calendar.getInstance();
		SimpleDateFormat dateFormat = new SimpleDateFormat("kk"+spStr+"mm"+spStr+"ss");
		return dateFormat.format(myCalendar.getTime());
	}
	//可以自定义分隔符的精确时间(到毫秒)
	public static String getAccTime(String spStr){
		myCalendar = Calendar.getInstance();
		SimpleDateFormat dateFormat = new SimpleDateFormat("kk"+spStr+"mm"+spStr+"ss"+spStr+"SS");
		return dateFormat.format(myCalendar.getTime());
	}

	//可以自定义分隔符的日期+时间
	public static String getDateTime(String spStr){
		myCalendar = Calendar.getInstance();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy"+spStr+"MM"+spStr+"dd"+spStr+"kk"+spStr+"mm"+spStr+"ss");
		return dateFormat.format(myCalendar.getTime());
	}
	
	//日期+时间
	public static String getAccDateTime(){
		myCalendar = Calendar.getInstance();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy年MM月dd日kk时mm分ss秒");
		return dateFormat.format(myCalendar.getTime());
	}
	//可以自定义分隔符的日期+时间
	public static String getAccDateTime(String spStr){
		myCalendar = Calendar.getInstance();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy"+spStr+"MM"+spStr+"dd"+spStr+"kk"+spStr+"mm"+spStr+"ss");
		return dateFormat.format(myCalendar.getTime());
	}
	
	//SQL Datetime专用
	public static String sqlDateTime(){
		myCalendar = Calendar.getInstance();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd"+" "+"kk:mm:ss:SS");
		return dateFormat.format(myCalendar.getTime());
	}
	
	//返回两个时间相差的天数
	public static int diffDay(String date1, String date2){
		myCalendar = Calendar.getInstance();

		Calendar c1 = Calendar.getInstance();
		Calendar c2 = Calendar.getInstance();
		
		Pattern timePattern = Pattern.compile("[^0-9]");
		int yyyy1 = Integer.parseInt(date1.split(timePattern.pattern())[0]);
		int MM1 = Integer.parseInt(date1.split(timePattern.pattern())[1]);
		int dd1   = Integer.parseInt(date1.split(timePattern.pattern())[2]);
		c1.set(Calendar.YEAR, yyyy1);
		c1.set(Calendar.MONTH, MM1-1);
		c1.set(Calendar.DATE,dd1);
		
		int yyyy2 = Integer.parseInt(date2.split(timePattern.pattern())[0]);
		int MM2 = Integer.parseInt(date2.split(timePattern.pattern())[1]);
		int dd2   = Integer.parseInt(date2.split(timePattern.pattern())[2]);
		c2.set(Calendar.YEAR, yyyy2);
		c2.set(Calendar.MONTH, MM2-1);
		c2.set(Calendar.DATE,dd2);
		
		double t1 = c1.getTimeInMillis();
		double t2 = c2.getTimeInMillis();
		
		double res = (t2-t1)/(1000*3600*24);
		if(res < 0){
			res = -res;
		}

		return (int)res;

	}
	
	//倒计时(天  时  分  秒)
	public static String countDown(String targetTime){
		myCalendar = Calendar.getInstance();
		
		Calendar targetC = Calendar.getInstance();
		Pattern timePattern = Pattern.compile("[^0-9]");
		String[] str = targetTime.split(timePattern.pattern());
		int[] times = new int[]{0,0,0,0,0,0};
		for(int i = 0 ; i < str.length ; i++){
			if(str[i] != null){
				times[i] = Integer.parseInt(str[i]);
			}
		}
		targetC.set(Calendar.YEAR, times[0]);
		targetC.set(Calendar.MONTH, times[1]-1);
		targetC.set(Calendar.DATE, times[2]);
		targetC.set(Calendar.HOUR_OF_DAY, times[3]);
		targetC.set(Calendar.MINUTE, times[4]);
		targetC.set(Calendar.SECOND, times[5]);
		
		long t1 = targetC.getTimeInMillis();
		long t2 = Calendar.getInstance().getTime().getTime();
		
		long res = (t2 - t1) > 0? (t2 - t1) : (t1 - t2);

		long resss = res/1000;
		long resmm = resss/60;
		long reskk = resmm/60;
		long resdd = reskk/24;
		
		resss = resss-resmm*60;
		resmm = resmm-reskk*60;
		reskk = reskk-resdd*24;
		
		return resdd+" 天 "+reskk+" 小时 "+resmm+" 分 "+resss+" 秒 ";
	}
	
	
	//获得各个日期时间个体变量
	public static int getDate_year() {
		myCalendar = Calendar.getInstance();
		int date_year = myCalendar.get(Calendar.YEAR);
		return date_year;
	}
	public static int getDate_month() {
		myCalendar = Calendar.getInstance();
		int date_month = myCalendar.get(Calendar.MONTH)+1;
		return date_month;
	}
	public static int getDate_date() {
		myCalendar = Calendar.getInstance();
		int date_date = myCalendar.get(Calendar.DATE);
		return date_date;
	}
	public static int getTime_hour() {
		myCalendar = Calendar.getInstance();
		int time_hour = myCalendar.get(Calendar.HOUR_OF_DAY);
		return time_hour;
	}
	public static int getTime_min() {
		myCalendar = Calendar.getInstance();
		int time_min = myCalendar.get(Calendar.MINUTE);
		return time_min;
	}
	public static int getTime_sec() {
		myCalendar = Calendar.getInstance();
		int time_sec = myCalendar.get(Calendar.SECOND);
		return time_sec;
	}
	public static int getTime_msec() {
		myCalendar = Calendar.getInstance();
		int time_msec = myCalendar.get(Calendar.MILLISECOND);
		return time_msec;
	}
}












