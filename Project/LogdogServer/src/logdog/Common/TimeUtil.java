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
}
