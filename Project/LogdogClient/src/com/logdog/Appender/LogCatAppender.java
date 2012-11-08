package com.logdog.Appender;

import java.util.Map;

import com.logdog.ErrorReport.ClientReportData;


/**
 * 로그켓에 로그 적용
 * @since 2012. 10. 13.오후 10:11:05
 * TODO 
 * @author JeongSeungsu
 */
public class LogCatAppender implements IAppender {

	com.google.code.microlog4android.appender.LogCatAppender appender;

	public boolean NetworkProcess(Map<String, String> SendData) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean ErrorReportProcess(ClientReportData Data) {
		// TODO Auto-generated method stub
		return false;
	}
	public void InitAppender() {
		// TODO Auto-generated method stub
		appender = new com.google.code.microlog4android.appender.LogCatAppender();
	}

	public com.google.code.microlog4android.appender.Appender GetAppender() {
		// TODO Auto-generated method stub
		return appender;
	}

	public String GetClassName() {
		// TODO Auto-generated method stub
		return null;
	}


	
	
}
