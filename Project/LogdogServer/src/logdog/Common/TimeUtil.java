package logdog.Common;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * 시간에 관련된 처리를 하기위한 요소들을 구현해놓은 클래스이다.
 * @since 2012. 11. 15.오전 6:10:01
 * TODO TimeCode Adder를 제작해야한다. 현재 단순 +을 하기 때문에 TimeCode 계산이 특정기간에 문제가 생길 수 있다.
 * @author Karuana
 */
public class TimeUtil {
	/**
	 *	현재 시간을 지닌 Date 객체를 리턴한다. 
	 * @since 2012. 11. 15.오전 6:14:03
	 * TODO
	 * @author Karuana
	 * @return 현재시간
	 */
	public static Date GetNowDate()
	{
		TimeZone.setDefault(TimeZone.getTimeZone("Asia/Seoul"));
	
		Calendar Temp = Calendar.getInstance();
	
		return Temp.getTime();
	}
	
	/**
	 *	주어진 년도에 최대 주수를 리턴한다.
	 * @since 2012. 11. 18.오후 8:24:53
	 * TODO
	 * @author Karuana
	 * @param Year
	 * @return
	 */
	public static int MaxWeekCount(int Year)
	{
		Calendar Adder = Calendar.getInstance();
		Adder.setTime(getCode2Date(Year,101));		
		return Adder.getActualMaximum(Calendar.WEEK_OF_YEAR);
	}
	
	/**
	 * 입력된 Date에 7일을 더한 뒤 리턴한다.
	 * @since 2012. 11. 15.오전 6:14:29
	 * TODO
	 * @author Karuana
	 * @param a
	 * @return a+7
	 */
	public static Date GetTimeWeekAdder(Date a) //HHMMSS int������ Ÿ���� ��� ����
	{
		Calendar Adder = Calendar.getInstance();
		Adder.setTime(a);
		Adder.add(Calendar.DATE, 7);

		return Adder.getTime();
	}
	
	/**
	 * 현재 시간이 주어진 시간보다 뒤인지 체크한다.
	 * @since 2012. 11. 15.오전 6:15:06
	 * TODO
	 * @author Karuana
	 * @param a
	 * @return
	 */
	public static boolean CheckNowTime(Date a)
	{
		Date nowTime =GetNowDate();
	//	System.out.println("A :" + a.toString());
	//	System.out.println("B :" + nowTime.toString());
		return a.before(nowTime);
	}
	
	/**
	 *	 a 시간이 b 시간보다 나중에 일어나면 true를 아니면 false를 리턴한다.
	 * @since 2012. 11. 15.오전 6:15:35
	 * TODO
	 * @author Karuana
	 * @param a
	 * @param b
	 * @return  boolean
	 */
	public static boolean CompareTime(Date a, Date b) //a�� b���� �����̸� true�ƴϸ� false 
	{
			return a.after(b);
	}
	
	/**
	 *	현재 년도 코드를 리턴한다.
	 * @since 2012. 11. 15.오전 6:16:15
	 * TODO
	 * @author Karuana
	 * @return Ex) 2012년 -> 2012
	 */
	public static int GetNowYear()
	{
		Date nowTime =GetNowDate();
		int Year = nowTime.getYear()+1900;
		
		return Year;
		
	}
	/**
	 *	오늘이 몇번째 주인지 리턴한다.
	 * @since 2012. 11. 18.오후 8:25:23
	 * TODO
	 * @author Karuana
	 * @return
	 */
	public static int GetWeek()
	{
		Calendar Adder = Calendar.getInstance();
		Adder.setTime(GetNowDate());
		return Adder.get(Calendar.WEEK_OF_YEAR);
	}
	
	/**
	 * 월과 일을 MMDD형태로 반환된다. 
	 * @since 2012. 11. 10.오전 12:35:37
	 * TODO
	 * @author Karuana
	 * @return Ex)11월 11일 -> 1111
	 */
	public static int GetNowTimeCode()
	{
		Date nowTime =GetNowDate();
		int Month = nowTime.getMonth()+1;
		int date = nowTime.getDate();
		int Timecode = Month*100 + date;
		return Timecode;
	} 
	
	/**
	 *	주어진 Date의 WeekCode를 리턴한다.
	 * @since 2012. 11. 18.오후 8:25:39
	 * TODO
	 * @author Karuana
	 * @param date
	 * @return
	 */
	public static int getWeekCode(Date date)
	{
		Calendar Adder = Calendar.getInstance();
		Adder.setTime(date);
		return Adder.get(Calendar.WEEK_OF_YEAR);
	}

	/**
	 *	Yearcdoe +DayCode를 Date로 변환한다.
	 * @since 2012. 11. 18.오후 8:26:00
	 * TODO
	 * @author Karuana
	 * @param year
	 * @param code
	 * @return
	 */
	public static Date getCode2Date(int year, int code)
	{
		Date tempDate=null;
		try{
			DateFormat sdFormat = new SimpleDateFormat("yyyy-MM-dd");
			tempDate = sdFormat.parse(year+"-"+code/100 +"-"+code%100);
			Calendar Adder = Calendar.getInstance();
			Adder.setTime(tempDate);
			tempDate = Adder.getTime();
		}catch(Exception e)
		{
			e.printStackTrace();
			return null;
		}
		
		return tempDate;
	}
	/**
	 *	Time Code의 값을 빼준다.
	 * @since 2012. 11. 16.오후 10:40:05
	 * TODO
	 * @author Karuana
	 * @param yeer
	 * @param date
	 * @param min	(is 음수)
	 * @return	datecode - min
	 */
	public static int minTimCode(int year,int date,int min)
	{
		Date tempDate=null;
		int Timecode =0;
		int dayCount = date%100;
	
		try{
			if( dayCount <= Math.abs(min))
			{
				DateFormat sdFormat = new SimpleDateFormat("yyyy-MM-dd");
				tempDate = sdFormat.parse(year+"-"+date/100 +"-"+date%100);
				Calendar Adder = Calendar.getInstance();
				Adder.setTime(tempDate);
				Adder.add(Calendar.DATE, min);
				tempDate = Adder.getTime();
				int Month = tempDate.getMonth()+1;
				int day = tempDate.getDate();
				Timecode = Month*100 + day;
	
			}
			else
			{	
				Timecode=date+min;
			}

		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return Timecode	;
		
	}
	
	/**
	 * 주어진 Time 객체를 String으로 변환해준다.
	 * @since 2012. 11. 15.오전 6:17:16
	 * TODO
	 * @author Karuana
	 * @param d
	 * @return yyyy-MM-dd 형태의 문자 
	 */
	public static String GetTime2String(Date d)
	{
		DateFormat sdFormat = new SimpleDateFormat("yyyy-MM-dd");

		String tempDate = sdFormat.format(d);
		return tempDate;
	}
}
