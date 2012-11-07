package com.logdog.Appender;

import com.logdog.Configuration.LogDogSetting;


/**
 * 로그켓에 로그 적용
 * @since 2012. 10. 13.오후 10:11:05
 * TODO 
 * @author JeongSeungsu
 */
public abstract class LogCatAppender implements Appender {

	com.google.code.microlog4android.appender.LogCatAppender appender;
	
	public LogCatAppender() {
		// TODO Auto-generated constructor stub
	}

	public void LogCatAppender(LogDogSetting setting) {
		// TODO Auto-generated method stub
		appender = new com.google.code.microlog4android.appender.LogCatAppender();
	}

	public com.google.code.microlog4android.appender.Appender GetAppender() {
		// TODO Auto-generated method stub
		return (com.google.code.microlog4android.appender.Appender) appender;
	}

}
