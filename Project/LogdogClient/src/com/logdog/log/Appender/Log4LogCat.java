package com.logdog.log.Appender;

import com.google.code.microlog4android.appender.Appender;
import com.google.code.microlog4android.appender.LogCatAppender;


/**
 * 
 * @since 2012. 10. 13.오후 10:11:05
 * TODO 로그켓에 로그 적용
 * @author JeongSeungsu
 */
public class Log4LogCat extends Log4Appender {

	LogCatAppender appender;
	
	public Log4LogCat() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void CreateAppender() {
		// TODO Auto-generated method stub
		appender = new LogCatAppender();
	}

	@Override
	public Appender GetAppender() {
		// TODO Auto-generated method stub
		return appender;
	}

}
