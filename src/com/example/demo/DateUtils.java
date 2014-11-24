package com.example.demo;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;
public class DateUtils {

	private final static String[] MONTHS = { "Jan", "Feb", "Mar", "Apr", "May",
			"Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec" };

	private final static DateFormat AFTER_SIX = new SimpleDateFormat(" yyyy");
	private final static DateFormat BEFORE_SIX = new SimpleDateFormat("HH:mm");
	private static Map<String,DateFormat> FormatterPool = new HashMap<String,DateFormat>();

	/**
	 * Get unix style date string.
	 */
	public static String getUnixDate(Date date) {
		long dateTime = date.getTime();
		if (dateTime < 0) {
			return "------------";
		}

		Calendar cal = new GregorianCalendar();
		cal.setTime(date);
		String firstPart = MONTHS[cal.get(Calendar.MONTH)] + ' ';

		String dateStr = String.valueOf(cal.get(Calendar.DATE));
		if (dateStr.length() == 1) {
			dateStr = ' ' + dateStr;
		}
		firstPart += dateStr + ' ';

		long nowTime = System.currentTimeMillis();
		if (Math.abs(nowTime - dateTime) > 183L * 24L * 60L * 60L * 1000L) {
			return firstPart + AFTER_SIX.format(date);
		} else {
			return firstPart + BEFORE_SIX.format(date);
		}
	}

	/**
	 * Get the timezone specific string.
	 */
	public static String getString(Date dt, DateFormat df, TimeZone to) {
		df.setTimeZone(to);
		return df.format(dt);
	}

	/**
	 * Get the timezone specific calendar.
	 */
	public static Calendar getCalendar(Date dt, TimeZone to) {
		Calendar cal = Calendar.getInstance(to);
		cal.setTime(dt);
		return cal;
	}

	/**
	 * Get date object.
	 */
	public static Date getDate(String str, DateFormat df, TimeZone from)
			throws java.text.ParseException {
		df.setTimeZone(from);
		return df.parse(str);
	}

	/**
	 * Get date difference => d1 - d2.
	 */
	public static String getDifference(Date d1, Date d2) {
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(d2);
		int year2 = calendar.get(Calendar.YEAR);
		int day2 = calendar.get(Calendar.DAY_OF_YEAR);
		int hour2 = calendar.get(Calendar.HOUR_OF_DAY);
		int min2 = calendar.get(Calendar.MINUTE);

		calendar.setTime(d1);
		int year1 = calendar.get(Calendar.YEAR);
		int day1 = calendar.get(Calendar.DAY_OF_YEAR);
		int hour1 = calendar.get(Calendar.HOUR_OF_DAY);
		int min1 = calendar.get(Calendar.MINUTE);

		int leftDays = (day1 - day2) + (year1 - year2) * 365;
		int leftHours = hour1 - hour2;
		int leftMins = min1 - min2;

		if (leftMins < 0) {
			leftMins += 60;
			--leftHours;
		}
		if (leftHours < 0) {
			leftHours += 24;
			--leftDays;
		}

		String interval = "";
		if (leftDays > 0) {
			interval = leftDays + " Days";
		} else if ((leftHours > 0) && (leftDays == 0)) {
			interval = leftHours + " Hours";
		} else if ((leftMins > 0) && (leftHours == 0) && (leftDays == 0)) {
			interval = leftMins + " Minutes";
		} else {
			interval = "";
		}
		return interval;
	}

	/**
	 * 鍙栧綋鍓嶆椂闂淬�
	 * <p>
	 * 榛樿鏍煎紡涓猴細yyyy-MM-dd HH:mm:ss銆�
	 * 
	 * @return 瀛楃涓茶〃绀虹殑褰撳墠鏃堕棿銆�
	 */
	public static String getDateTime() {
		return getDateTime("yyyy-MM-dd HH:mm:ss");
	}

	public static String getDateOtherTime() {
		return getDateTime("yyyy-MM-dd HH-mm-ss SSS");
	}

	/**
	 * 鏍规嵁缁欏畾鐨勬牸寮忓彇褰撳墠鏃堕棿銆�
	 * <p>
	 * 濡傛灉缁欏畾鐨勬牸寮忎负绌猴紝鍒欎娇鐢ㄩ粯璁ゆ牸寮忥細yyyy-MM-dd HH:mm:ss銆�
	 * 
	 * @param pattern
	 *            鎸囧畾鏍煎紡
	 * @return 瀛楃涓茶〃绀虹殑褰撳墠鏃堕棿
	 */
	public static String getDateTime(String pattern) {
		if (null == pattern || "".equals(pattern)) {
			pattern = "yyyy-MM-dd HH:mm:ss";
		}		
		return getFormatter(pattern).format(Calendar.getInstance().getTime());
	} // end getDateTime()

	public static String fotmatDate3(Date myDate) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String strDate = formatter.format(myDate);
		return strDate;
	}

	public static String fotmatDate4(Date myDate) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String strDate = formatter.format(myDate);
		return strDate;
	}

	public static String fotmatDate5(Date myDate) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
		String strDate = formatter.format(myDate);
		return strDate;
	}

	public static String fotmatDate6(Date myDate) {
		SimpleDateFormat formatter = new SimpleDateFormat("MM-dd HH:mm");
		String strDate = formatter.format(myDate);
		return strDate;
	}

	public static String fotmatDate7(Date myDate) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
		String strDate = formatter.format(myDate);
		return strDate;
	}

	public static String fotmatDate8(Date myDate) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		String strDate = formatter.format(myDate);
		return strDate;
	}
	
	public static String fotmatDate9(Date myDate) {
		SimpleDateFormat formatter = new SimpleDateFormat("HHmmss");
		String strDate = formatter.format(myDate);
		return strDate;
	}
	//20100721  ---> 2010-07-21
	public static String fotmatDate10(int date) {
		String dateStr = String.valueOf(date);
		return fotmatDate4(getDate(dateStr));
	}
	
	public static String fotmatDate11(Date myDate) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMM");
		String strDate = formatter.format(myDate);
		return strDate;
	}
	//20100721   112030 ---> 2010-07-21 11:20:30
	public static String fotmatDate10(int date, int time) {
		String dateStr = String.valueOf(date);
		String timeStr = String.valueOf(time);
		int ch = 0;
		if((ch = 6 - timeStr.length()) != 0){
			for(int i = 0; i < ch;i++){
				timeStr = "0"+timeStr;
			}
		}
		return fotmatDate3(getDateAndTime(dateStr+" "+timeStr));
	}
	
	public static int getCurrentDate(){
		return Integer.parseInt(fotmatDate7(new Date()));
	}
	
	public static int getCurYMDate(){
		return Integer.parseInt(fotmatDate11(new Date()));
	}
	
	public static int getSpecialDate(String dateStr){// 2004-7-23
		long milltime = getDateLongTime(dateStr);
		return Integer.parseInt(fotmatDate7(new Date(milltime)));
	}
	
	public static int getCurrentTime(){
		return Integer.parseInt(fotmatDate9(new Date()));
	}
	
	public static long getLongTime() {
		Date aDate = new Date();
		long atime = aDate.getTime();
		// System.out.println(atime);
		return atime;
	}

	public static long getaLongTime() {
		return System.currentTimeMillis();
	}

	public static long getDateLongTime(int year, int month, int day) {
		Calendar myCalendar = Calendar.getInstance();
		myCalendar.set(year, month - 1, day);
		return myCalendar.getTime().getTime();
	}

	public static long getDateLongTime(String dateStr) { // 2004-7-23
		long value = 0;
		try {
			SimpleDateFormat myFormatter = new SimpleDateFormat("yyyy-MM-dd");
			java.util.Date date = myFormatter.parse(dateStr);
			value = date.getTime();
		} catch (java.text.ParseException ex) {
			ex.printStackTrace();
		}
		return value;
		// Calendar myCalendar = Calendar.getInstance();
		// myCalendar.set(year, month - 1, day);
		// return myCalendar.getTime().getTime();
	}

	public static long getDateLongTimeStr(String dateStr) { // 2004-7-23
		// 18:40:40
		long value = 0;
		try {
			SimpleDateFormat myFormatter = new SimpleDateFormat(
					"yyyy-MM-dd HH:mm:ss");
			java.util.Date date = myFormatter.parse(dateStr);
			value = date.getTime();
		} catch (java.text.ParseException ex) {
			ex.printStackTrace();
		}
		return value;
	}
	
	public static Date getDate(String dateStr) { // 20040723
		try {
			SimpleDateFormat myFormatter = new SimpleDateFormat("yyyyMMdd");
			java.util.Date date = myFormatter.parse(dateStr);
			return date;
		} catch (java.text.ParseException ex) {
			ex.printStackTrace();
		}
		return null;
	}
	
	public static Date getDateAndTime(String dateStr) { // 20040723 112033
		try {
			SimpleDateFormat myFormatter = new SimpleDateFormat("yyyyMMdd HHmmss");
			java.util.Date date = myFormatter.parse(dateStr);
			return date;
		} catch (java.text.ParseException ex) {
			ex.printStackTrace();
		}
		return null;
	}
	/**
	 * <p>浣滆�:lht</p>
	 * <p>鍔熻兘鎻忚堪:鏃ユ湡瀛楃涓茶浆鎹㈡垚Date瀵硅薄</p>
	 * <p>鍒涘缓鏃堕棿:2012-9-19涓嬪崍4:04:45</p>
	 * <p>@param dateStr
	 * <p>@param pattern 
	 * <p>@return</p>
	 * <p>淇敼:</p>
	 */
	public static Date getDateFromString(String dateStr,String pattern) { // 20040723 112033
		try {
			java.util.Date date = getFormatter( pattern).parse(dateStr);
			return date;
		} catch (java.text.ParseException ex) {
			ex.printStackTrace();
		}
		return null;
	}
	public static long getDateLongTimeStr2(String dateStr) { // 2004-7-23
		// 18:40
		long value = 0;
		try {
			SimpleDateFormat myFormatter = new SimpleDateFormat(
					"yyyy-MM-dd HH:mm");
			java.util.Date date = myFormatter.parse(dateStr);
			value = date.getTime();
		} catch (java.text.ParseException ex) {
			ex.printStackTrace();
		}
		return value;
	}

	/**
	 * 
	 * @param dateStr
	 *            String
	 * @param pattern
	 *            String "yyyy骞碝M鏈坉d鏃�HH鏃秏m鍒唖s绉� or 2004-7-23 18:40 etc
	 * @return long
	 */
	public static long getCommonDateLongTimeStr(String dateStr, String pattern) { // 2004-7-23
		// 18:40
		long value = 0;
		try {
			SimpleDateFormat myFormatter = new SimpleDateFormat(pattern);
			java.util.Date date = myFormatter.parse(dateStr);
			value = date.getTime();
		} catch (java.text.ParseException ex) {
			ex.printStackTrace();
		}
		return value;
	}

	// public static String fotmatDate1(Date myDate) {
	// SimpleDateFormat formatter = new SimpleDateFormat("yyyy骞碝M鏈坉d鏃�
	// HH鏃秏m鍒唖s绉�);
	// String strDate = formatter.format(myDate);
	// return strDate;
	// }



	public static String getDateFormatStr2(long val) {
		return fotmatDate3(new Date(val));
	}

	public static String getDateFormatSimpleStr(long val) {
		return fotmatDate4(new Date(val));
	}

	public static String getDateFormatNow() {
		return fotmatDate4(new Date());
	}

	public static String getDateFormatNow7() {
		return fotmatDate7(new Date());
	}

	public static String getDateTimeStr(long val) {
		return fotmatDate8(new Date(val));
	}

	public static String getDateTimeStr2(long val) {
		return fotmatDate3(new Date(val));
	}

	public static String getDateTimeStrWithDate(long val) {
		return fotmatDate4(new Date(val));
	}

	

	public static String getTimeMonthsAgo(final int counts) {
		java.util.Date myDate = new java.util.Date();
		long myTime = (myDate.getTime() / 1000) - 60 * 60 * 24 * 30 * counts;
		myDate.setTime(myTime * 1000);
		String mDate = fotmatDate4(myDate); // this.getDateFormatSimpleStr()
		// formatter.format(myDate);
		return mDate;
	}

	public static String getTimeDaysAgo(final int counts) {
		java.util.Date myDate = new java.util.Date();
		long myTime = (myDate.getTime() / 1000) - 60 * 60 * 24 * counts;
		myDate.setTime(myTime * 1000);
		String mDate = fotmatDate4(myDate); // this.getDateFormatSimpleStr()
		// formatter.format(myDate);
		return mDate;
	}

	public static String getTimeDaysAfter(final int counts) {
		java.util.Date myDate = new java.util.Date();
		long myTime = (myDate.getTime() / 1000) + 60 * 60 * 24 * counts;
		myDate.setTime(myTime * 1000);
		String mDate = fotmatDate3(myDate); // this.getDateFormatSimpleStr()
		// formatter.format(myDate);
		return mDate;
	}

	public static long getTimeDaysAfterCount(final int counts) {
		java.util.Date myDate = new java.util.Date();
		long myTime = (myDate.getTime() / 1000) + 60 * 60 * 24 * counts;
		return myTime * 1000;
	}

	public static long getTimeDaysAfter(String dateStr, final int counts) { // dateStr
		// 2004-7-23
		java.util.Date myDate = new java.util.Date();

		long myTime = (getDateLongTime(dateStr) / 1000) + 60 * 60 * 24 * counts;
		myDate.setTime(myTime * 1000);
		// String mDate = fotmatDate3(myDate); //this.getDateFormatSimpleStr()
		// formatter.format(myDate);
		return myDate.getTime();
	}

	/**
	 * 鍒ゆ柇鎸囧畾鏃堕棿鏄惁鏄嚑澶╁唴
	 * 
	 * @param compareTime
	 * @param days
	 * @return
	 */
	public static boolean isTimeDaysRecent(long compareTime, int days) {
		long nowTime = getLongTime();
		long day = (nowTime - compareTime) / (24 * 60 * 60 * 1000);
		if (day > days) {
			return false;
		} else {
			return true;
		}
	}
	
	//鎸囧畾鏃堕棿锛屾寚瀹氬ぉ鏁颁箣鍚庣殑鏃ユ湡涓庡綋鍓嶆椂闂寸殑宸�
	public static Long getRangeOfDaysAddDate(String date,Integer days) throws Exception
	{
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date myDate = formatter.parse(date);
		Long myTime = (myDate.getTime() / 1000) + 60 * 60 * 24 * days;
		myDate.setTime(myTime * 1000);
		String mDate = formatter.format(myDate);  //鎸囧畾鏃ユ湡鍚庣殑澶╂暟
		
		// 涓や釜鏃堕棿涔嬮棿鐨勫ぉ鏁�
		java.util.Date nowdate = null;
		java.util.Date mydate = null;
		try {
			nowdate = formatter.parse(fotmatDate10(DateUtils.getCurrentDate()));  //褰撳墠鏃堕棿
			mydate = formatter.parse(mDate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		long day = (mydate.getTime() - nowdate.getTime() )
				/ (24 * 60 * 60 * 1000);
		
		return day;
	}

	/**
	 * 鍙栧緱2涓椂闂寸浉宸殑澶╂暟
	 * 
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static long getTwoDateDifferentDay(String date1, String date2) {// 2003-05-1
		long day = -1;
		try {
			SimpleDateFormat myFormatter = new SimpleDateFormat("yyyy-MM-dd");
			java.util.Date date = myFormatter.parse(date1);
			java.util.Date mydate = myFormatter.parse(date2);
			day = (date.getTime() - mydate.getTime()) / (24 * 60 * 60 * 1000);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return day;
	}
	
	/**
	 * 
	 * <p>浣滆�:lht</p>
	 * <p>鍔熻兘鎻忚堪:鑾峰彇鏌愬ぉ璺濈浠婂ぉ鐨勫ぉ鏁�浠婂ぉ-鎸囧畾鏃ユ湡)</p>
	 * <p>鍒涘缓鏃堕棿:2012-8-10涓嬪崍3:00:14</p>
	 * <p>@param oneDay
	 * <p>@return</p>
	 * <p>淇敼:</p>
	 */
	public static long getDatesOfOneDayToNow(String oneDay){
		return getTwoDateDifferentDay(getCurrentTime("yyyy-MM-dd"),oneDay);		
	}

	

	// 姹傚嚭鏈湀鐨勭涓�ぉ鍜屾渶鍚庝竴澶�
	public static String[] monthFix() {
		String[] dateMonth = new String[2];
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Calendar calendar = Calendar.getInstance();
		Calendar cpcalendar = (Calendar) calendar.clone();
		// 鏈湀绗竴澶�
		cpcalendar.set(Calendar.DAY_OF_MONTH, 1);
		dateMonth[0] = df.format(new Date(cpcalendar.getTimeInMillis()))
				.concat(" 00:00:01");

		cpcalendar.add(Calendar.MONTH, 1);
		cpcalendar.add(Calendar.DATE, -1);
		dateMonth[1] = df.format(new Date(cpcalendar.getTimeInMillis()))
				.concat(" 23:59:59");
		return dateMonth;
	}

	//// 姹傚嚭鏈懆鐨勭涓�ぉ鍜屾渶鍚庝竴澶�
	public static String[] dayOfWeekFix() {
		String[] dateDay = new String[2];
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Calendar calendar = Calendar.getInstance();
		Calendar cpcalendar = (Calendar) calendar.clone();

		cpcalendar.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
		dateDay[0]=df.format(new Date(cpcalendar.getTimeInMillis())).concat(" 00:00:01");
		
		cpcalendar.set(Calendar.DAY_OF_WEEK, Calendar.SATURDAY);		
		dateDay[1]=df.format(new Date(cpcalendar.getTimeInMillis())).concat(" 23:59:59");		
		return dateDay;
	}

	public static Integer parseInt(String dateStr){
		Integer date = new Integer(0);
		date = Integer.parseInt(dateStr.substring(0,4));
		date=date*100+Integer.parseInt(dateStr.substring(5,7));
		date=date*100+Integer.parseInt(dateStr.substring(8,10));
		return date;
	}
	public static Date getCurrentTimeForDate() {
		    return Calendar.getInstance().getTime();
		  }
	public static String getCurrentTime(String pattern) {
		    return formatTime(pattern, getCurrentTimeForDate());
		  }
	public static String formatTime(String pattern, Date time) {
		    return getFormatter(pattern).format(time);
		  }
	/**
	 * 
	 * <p>浣滆�:lht</p>
	 * <p>鍔熻兘鎻忚堪:鏃ユ湡鏍峰紡 瀵硅薄鏀捐繘闈欐�鍙橀噺锛屾瘡娆″厛浠庨潤鎬佺殑Map瀵硅薄涓鍙栵紝濡傛灉娌℃湁灏辨柊寤烘牱寮忓苟鏀惧叆闈欐�瀵硅薄锛屽彲浠ヨ妭鐪佸唴瀛樼┖闂�/p>
	 * <p>鍒涘缓鏃堕棿:2012-7-25涓嬪崍2:19:01</p>
	 * <p>@param pattern
	 * <p>@return</p>
	 * <p>淇敼:</p>
	 */
	private static DateFormat getFormatter(String pattern) {
		    DateFormat df =  FormatterPool.get(pattern);
		    if (df == null) {
		      df = new SimpleDateFormat(pattern);
		      FormatterPool.put(pattern, df);
		    }
		    return df;
		  }

	  /**
	   * 鍒ゆ柇涓や釜鏃ユ湡鏄惁鍚屼竴澶�
	   * 濡傛灉鏃ユ湡閮戒负绌猴紝鍒欒繑鍥瀎alse
	   * @param d1 Date
	   * @param d2 Date
	   * @return boolean
	   */
	  public static boolean isSameDay(Date d1, Date d2) {
	    if (d1 == null || d2 == null) {
	      return false;
	    }
	    Calendar c1 = Calendar.getInstance();
	    c1.setTime(d1);
	    Calendar c2 = Calendar.getInstance();
	    c2.setTime(d2);
	    if (c1.get(Calendar.DAY_OF_YEAR) != c2.get(Calendar.DAY_OF_YEAR)) {
	      return false;
	    }
	    if (c1.get(Calendar.YEAR) != c2.get(Calendar.YEAR)) {
	      return false;
	    }
	    return true;

	  }
	  /**
	   *    
	   * <p>浣滆�:lht</p>
	   * <p>鍔熻兘鎻忚堪:姣旇緝缁欏畾鏃ユ湡鏄惁澶т簬褰撳墠鏃ユ湡</p>
	   * <p>鍒涘缓鏃堕棿:2012-10-11涓婂崍10:31:32</p>
	   * <p>@param dateA 鏃ユ湡鏍煎紡锛歽yyy-MM-dd HH:mm:ss
	   * <p>@return
	   * <p>@throws ParseException</p>
	   * <p>淇敼:</p>
	   */
	  public static boolean compareDateABiggerThanCurrentDate(String dateA) throws ParseException{
	 	 Date a = getDateFromString(dateA, "yyyy-MM-dd HH:mm:ss");
	 	 return a.after(Calendar.getInstance().getTime());
	  }
	
	/**
	 鍦ㄥ綋鍓嶆棩鏈熷熀纭�笂澧炲姞鏈堜唤鏁�
	 @param month鏈堜唤鏁�
	 @return 娣诲姞鍚庢牸寮忓寲鐨勬椂闂�
	 @Date:   2012-09-14
	*/
	public static String addDate(int month) {
		Calendar c = Calendar.getInstance();
		c.add(Calendar.MONTH, month);
		return getFormatter("yyyy-MM-dd HH:mm:ss").format(c.getTime());
	}

	public static void main(String[] argv) {
		// for(int i=0;i<1000;i++){
		// System.out.println(DateUtils.getDateOtherTime());
		// }
		// 1242122853859
		// 1242122702
		// System.out.println(DateUtils.getDateFormatStr2(1242123195000L));
		// System.out.println(DateUtils.getLongTime());
		// System.out.println(System.currentTimeMillis());
//		System.out.println(monthFix()[0]);
//		System.out.println(monthFix()[1]);
//		System.out.println(dayOfWeekFix()[0]);
//		System.out.println(dayOfWeekFix()[1]);
//		System.out.println(fotmatDate10(20100605));
//		System.out.println(fotmatDate10(20100605,4));
//		System.out.println(parseInt("2010-12-12"));
	//	System.out.println("==="+addDate(0));
		try {
//			System.out.println(DateUtils.getRangeOfDaysAddDate("2010-6-20",0));
			System.out.println("==="+getDatesOfOneDayToNow("2012-10-29"));
//			System.out.println("==="+getTwoDateDifferentDay("2012-8-21","2012-8-20"));
//			System.out.println("==="+getTwoDateDifferentDay("2012-8-20","2012-8-21"));
//			System.out.println("==="+getTwoDateDifferentDay("2012-8-20 10:00:00","2012-8-21 08:59:59"));
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
