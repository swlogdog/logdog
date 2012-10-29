package com.logdog.util;

import java.text.SimpleDateFormat;
import java.util.Locale;

import android.content.Context;

public class Date {

	public Date() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * "yyyy.MM.dd HH:mm:ss" string으로 변환
	 * @since 2012. 10. 29.오후 8:13:58
	 * TODO
	 * @author JeongSeungsu
	 * @return
	 */
	public static String GetDateYYMMDDHHMMSS(Context context){
		Date currenttime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat ( "yyyy.MM.dd HH:mm:ss",  context.getResources( ).getConfiguration( ).locale);
		String time = formatter.format(currenttime);
		return time;
	}

}
