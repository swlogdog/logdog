package com.logdog.common.Date;

import java.text.SimpleDateFormat;
import android.content.Context;

/**
 * 단순한 시간 Date값을 위한 클래스
 * @since 2012. 11. 11.오후 11:53:51
 * TODO
 * @author JeongSeungsu
 */
public class Date {

	/**
	 * 시간값으 가져온다.
	 * "yyyy.MM.dd HH:mm:ss" string으로 변환
	 * @since 2012. 10. 29.오후 8:13:58
	 * TODO
	 * @author JeongSeungsu
	 * @return "yyyy.MM.dd HH:mm:ss"
	 */
	public static String GetDateYYMMDDHHMMSS(Context context){
		java.util.Date currenttime = new java.util.Date();
		SimpleDateFormat formatter = new SimpleDateFormat ( "yyyy.MM.dd HH-mm-ss",  context.getResources( ).getConfiguration( ).locale);
		String time = formatter.format(currenttime);
		return time;
	}

}
