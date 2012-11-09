package logdog.Common;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class TimeUtil {
	public static Date GetNowDate()
	{
		TimeZone.setDefault(TimeZone.getTimeZone("Asia/Seoul"));
	
		Calendar Temp = Calendar.getInstance();
	
		return Temp.getTime();
	}
	
	
	
	public static Date GetTimeWeekAdder(Date a) //HHMMSS int������ Ÿ���� ��� ����
	{
		Calendar Adder = Calendar.getInstance();
		Adder.setTime(a);
		Adder.add(Calendar.DATE, 7);

		return Adder.getTime();
	}
	
	public static boolean CheckNowTime(Date a)
	{
		Date nowTime =GetNowDate();
	//	System.out.println("A :" + a.toString());
	//	System.out.println("B :" + nowTime.toString());
		return a.before(nowTime);
	}
	
	public static boolean CompareTime(Date a, Date b) //a�� b���� �����̸� true�ƴϸ� false 
	{
			return a.after(b);
	}
	
	public static int GetNowYear()
	{
		Date nowTime =GetNowDate();
		int Year = nowTime.getYear()+1900;
		
		return Year;
		
	}
	
	/**
	 *		MMDD형태로 반환된다. 
	 * @since 2012. 11. 10.오전 12:35:37
	 * TODO
	 * @author Karuana
	 * @return
	 */
	public static int GetNowTimeCode()
	{
		Date nowTime =GetNowDate();
		int Month = nowTime.getMonth()+1;
		int date = nowTime.getDate();
		int Timecode = Month*100 + date;
		return Timecode;
	}
}
