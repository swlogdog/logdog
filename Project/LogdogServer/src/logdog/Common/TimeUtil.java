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
