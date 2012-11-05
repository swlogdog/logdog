package com.logdog.log.Appender;

import com.google.code.microlog4android.appender.Appender;
import com.google.code.microlog4android.appender.LogCatAppender;
import com.logdog.Setting.LogDogSetting;


/**
 * 로그켓에 로그 적용
 * @since 2012. 10. 13.오후 10:11:05
 * TODO 
 * @author JeongSeungsu
 */
public class Log4LogCat implements Log4Appender {

	LogCatAppender appender;
	
	public Log4LogCat() {
		// TODO Auto-generated constructor stub
	}

	public void CreateAppender(LogDogSetting setting) {
		// TODO Auto-generated method stub
		appender = new LogCatAppender();
	}

	public Appender GetAppender() {
		// TODO Auto-generated method stub
		return appender;
	}

}
