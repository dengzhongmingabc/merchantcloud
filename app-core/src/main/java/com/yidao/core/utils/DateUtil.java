package com.yidao.core.utils;

import org.apache.commons.lang3.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {

	public static Date getDateByStr(String dateStr) {
		SimpleDateFormat formatter = null;
		if (dateStr.length() == 10)
			formatter = new SimpleDateFormat("yyyy-MM-dd");
		else if (dateStr.length() == 16)
			formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		else if (dateStr.length() == 19)
			formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		else {
			System.out.println("字符错误!");
			return null;
		}
		try {
			return formatter.parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}
	

	/**得到指定格式的字符串
	 */
	public static String getStrByDate(Date date, String format) {
		SimpleDateFormat formatter = new SimpleDateFormat(format);
		return date!=null?formatter.format(date):"";
	}

	/**
	 *
	 * @param date
	 * @return yyyy-MM-dd
	 */
	public static String getStrYMDByDate(Date date) {
		return getStrByDate(date, "yyyy-MM-dd");
	}
	
	
	public static String getStrYMByDate(Date date) {
		return getStrByDate(date, "yyyy-MM");
	}
	
	public static String getStrHHByDate(Date date) {
		return getStrByDate(date, "HH");
	}

	
	public static String getStrAddWeekByDate(Date date) {
		long times = date.getTime()+1000*60*60*24*7;
		Date addWeekDate = new Date(times);
		return getStrByDate(addWeekDate, "yyyy-MM-dd");
	}
	
	public static String getHHStrAddWeekByDate(Date date) {
		long times = date.getTime()+1000*60*60*24*7;
		Date addWeekDate = new Date(times);
		return getStrByDate(addWeekDate, "HH");
	}
	/**
	 *
	 * @param date
	 * @return HH:mm:ss
	 */
	public static String getStrHMSByDate(Date date) {
		return getStrByDate(date, "HH:mm:ss");
	}

	/**
	 *
	 * @param date
	 * @return yyyy-MM-dd HH:mm:ss
	 */
	public static String getStrYMDHMSByDate(Date date) {
		return getStrByDate(date, "yyyy-MM-dd HH:mm:ss");
	}
	
	public static String getStrByDate(Date date) {
		return getStrByDate(date, "yyyyMMddHHmmss");
	}
	
	public static String dateToShortCode() {
		Date date = new Date();
		SimpleDateFormat simpledateformat = new SimpleDateFormat(
				"yyyyMMdd");
		String s = simpledateformat.format(date);
		return s;
	}

	/**
	 *
	 * @param date
	 * @return yyyy-MM-dd HH:mm
	 */
	public static String getStrYMDHMByDate(Date date) {
		return getStrByDate(date, "yyyy-MM-dd HH:mm");
	}

	public static String getStrYMDHByDate(Date date) {
		return getStrByDate(date, "yyyy-MM-dd:HH");
	}
	
	public static String getStrYMDHByDate2(Date date) {
		return getStrByDate(date, "yyyy-MM-dd HH");
	}
	/**
	 */
	public static Date addDay(Date date, Integer days) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DATE, days);
		return calendar.getTime();
	}
	
	public static Date addTime(Date date, Long time) {
		return new Date(date.getTime()+time);
	}

	/**
	 */
	public static Date addMonth(Date date, Integer months) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MONTH, months);
		return calendar.getTime();
	}

	/**
	 * 杩斿洖涓枃鏃堕棿鏍煎紡
	 * @param object 鍙互涓篋ate瀵硅薄锟?2007-06-12鏍煎紡鐨勫瓧绗︿覆
	 * @return
	 */
	public static String toChinese(Object object) {
		String dateStr = null;
		if (object instanceof Date)
			dateStr = getStrYMDByDate((Date) object);
		else if (object instanceof String)
			dateStr = (String) object;
		else
			return dateStr;
		String[] cnArray = { "锟?", "锟?", "锟?", "锟?", "锟?", "锟?", "锟?", "锟?", "锟?", "锟?" };
		String year = dateStr.split("-")[0];
		String month = dateStr.split("-")[1];
		String date = dateStr.split("-")[2];
		dateStr = "";
		for (int i = 0; i < year.length(); i++)
			dateStr += cnArray[Integer.valueOf(String.valueOf(year.charAt(i)))];
		dateStr += "锟?";
		if ("10".equals(month))
			dateStr += "锟?";
		else {
			int num = Integer.valueOf(String.valueOf(month.charAt(1)));
			if ("0".equals(String.valueOf(month.charAt(0))))
				dateStr += cnArray[num];
			else
				dateStr += "锟?" + cnArray[num];
		}
		dateStr += "锟?";
		if ("10".equals(date))
			dateStr += "锟?";
		else {
			String temp = String.valueOf(date.charAt(0));
			if ("1".equals(temp))
				dateStr += "锟?";
			else if ("2".equals(temp))
				dateStr += "浜屽崄";
			else if ("3".equals(temp))
				dateStr += "涓夊崄";
			if (!"0".equals(String.valueOf(date.charAt(1))))
				dateStr += cnArray[Integer.valueOf(String.valueOf(date.charAt(1)))];
		}
		dateStr += "锟?";
		return dateStr;
	}

	/**
	 * 杩斿洖鏄熸湡锟?
	 * @param object Date瀵硅薄鎴栵拷?锟藉瓧绗︿覆,yyyy-MM-dd
	 * @return 鏄熸湡锟?
	 */
	@SuppressWarnings("deprecation")
	public static String getWeek(Object object) {
		Date date = null;
		if (object instanceof Date)
			date = (Date) object;
		else if (object instanceof String)
			date = getDateByStr((String) object);
		else
			return "";
		String[] cnWeek = { "锟?", "锟?", "锟?", "锟?", "锟?", "锟?", "锟?" };
		return "鏄熸湡" + cnWeek[date.getDay()];
	}

	public static Date get00_00_00Date(Date date) {
		return getDateByStr(getStrYMDByDate(date));
	}

	public static Date get23_59_59Date(Date date) {
		return getDateByStr(getStrYMDHMSByDate(date).substring(0, 10) + " 23:59:59");
	}

	public static Integer changeSecond(String hms) {
		if (hms == null || "".equals(hms)) {
			return null;
		}

		String[] t = hms.split(":");
		int hour = Integer.valueOf("0" + t[0]);
		int min = Integer.valueOf("0" + t[1]);
		int sec = Integer.valueOf("0" + t[2]);

		return hour * 3600 + min * 60 + sec;
	}

	/**
	 * 杩斿洖澶氬皯鏃堕棿锟?
	 * @param date
	 * @return
	 */
	public static String getPreTime(Date date) {
		long time = date.getTime();
		Long result = 0L;
		result = (new Date().getTime() - time);
		result = result / 1000L;
		if (result < 60L) {
			return result + "绉掑墠";
		}
		if (result >= 60L && result < 3600L) {
			result = result / 60;
			return result + "鍒嗛挓锟?";
		}
		if (result >= 3600L && result < 86400L) {
			result = result / 3600;
			return result + "灏忔椂锟?";
		} else {
			result = result / 3600 / 24;
			return result + "澶╁墠";
		}
	}

	/**
	 * 鍙栦紶鍏ユ棩鏈熷悗鐨勭锟?涓槦鏈熶竴(鍖呮嫭浼犲叆鐨勬棩锟?)
	 * @param startTime
	 * @return
	 */
	public static Date getFirstMondayAfter(Date startTime) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(startTime);
		while (cal.get(Calendar.DAY_OF_WEEK) != Calendar.MONDAY) {
			cal.add(Calendar.DATE, 1);
		}
		return cal.getTime();
	}

	/**
	 * 褰撳墠锟?
	 * @return
	 */
	public static int getCurrYear() {
		Calendar cal = Calendar.getInstance();
		return cal.get(Calendar.YEAR);
	}

	/**
	 * 褰撳墠锟?
	 * @return
	 */
	public static int getCurrMonth() {
		Calendar cal = Calendar.getInstance();
		return cal.get(Calendar.MONTH) + 1;
	}

	/**
	 * 褰撳墠锟?
	 * @return
	 */
	public static int getCurrDay() {
		Calendar cal = Calendar.getInstance();
		return cal.get(Calendar.DAY_OF_MONTH);
	}

	/**
	 * 锟?
	 * @return
	 */
	public static int getYear(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal.get(Calendar.YEAR);
	}

	/**
	 * 锟?
	 * @return
	 */
	public static int getMonth(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal.get(Calendar.MONTH) + 1;
	}

	/**
	 * 锟?
	 * @return
	 */
	public static int getDay(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal.get(Calendar.DAY_OF_MONTH);
	}

	/**
	 * 鍙栦紶鍏ユ椂闂寸殑褰撴湀绗竴锟?
	 * @param date
	 * @return
	 */
	public static Date getFirstDay(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.DATE, cal.getActualMinimum(Calendar.DATE));
		return cal.getTime();
	}

	/**
	 * 鍙栦紶鍏ユ椂闂寸殑褰撴湀锟?鍚庝竴锟?
	 * @param date
	 * @return
	 */
	public static Date getLastDay(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.DATE, cal.getActualMaximum(Calendar.DATE));
		return cal.getTime();
	}
	
	public static String getFristTime(String yearStr,String monthstr){
		if(StringUtils.isNotEmpty(yearStr) && StringUtils.isNotEmpty(monthstr)){
			return yearStr+"-"+monthstr+"-"+"01 00:00:00";
		}
		return null;
	}
	
	public static String getLastTime(String yearStr,String monthstr){
		if(StringUtils.isEmpty(yearStr) || StringUtils.isEmpty(monthstr)){
			return null;
		}
		int month=Integer.parseInt(monthstr);
		String LastTime=null;
		if(month==1 || month==3 || month==5 || month==7 || month==8 || month==10 || month==12){
			LastTime=yearStr+"-"+monthstr+"-"+"31 59:59:59";
		}else if(month==2){
			int year=Integer.parseInt(yearStr);
			if(year%4==0){
				LastTime=yearStr+"-"+monthstr+"-"+"29 59:59:59";
			}else{
				LastTime=yearStr+"-"+monthstr+"-"+"28 59:59:59";
			}
		}else{
			LastTime=yearStr+"-"+monthstr+"-"+"30 59:59:59";
		}
		return LastTime;
	}
	
	public static int getQuarterInMonth(int month, boolean isQuarterStart) {  
        int months[] = { 1, 4, 7, 10 };  
        if (!isQuarterStart) {  
            months = new int[] { 3, 6, 9, 12 };  
        }  
        if (month >= 2 && month <= 4)  
            return months[0];  
        else if (month >= 5 && month <= 7)  
            return months[1];  
        else if (month >= 8 && month <= 10)  
            return months[2];  
        else  
            return months[3];  
    }  
	
	public static int getPreQuarter(int currentMonth,boolean isQuarterStart){
		return getQuarterInMonth(currentMonth-3,isQuarterStart);
	}
	
	public static int getQuarter(int currentMonth){
		
		if(currentMonth>=1&&currentMonth<=3){
			return 1;
		}
		if(currentMonth>=4&&currentMonth<=6){
			return 2;
		}
		if(currentMonth>=7&&currentMonth<=9){
			return 3;
		}
		if(currentMonth>=10&&currentMonth<=12){
			return 4;
		}
		return -1;
	}
	
	//计算两个日期之间的天数
	public static int differentDaysByMillisecond(Date before,Date after)
    {
		if(before==null||after==null||before.getTime()>after.getTime()){
			return 0;
		}
		if(DateUtil.getStrYMDByDate(before).equals(DateUtil.getStrYMDByDate(after))){
			return 0;
		}
        Date _b = DateUtil.get00_00_00Date(addDay(before,1));
        Date _a = DateUtil.get00_00_00Date(after);
        return (int)((_a.getTime()-_b.getTime())/1000*3600*24)+1;
    }
}
