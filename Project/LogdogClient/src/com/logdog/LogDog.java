package com.logdog;
/*
 * 더블 체크드 락킹....
 * 팩토리로 만들어서 사용자가 팩토리를 사용하도록 
 * 4개의 레벨, 어펜더, 포매터, 리포트 
 * 서비스 형태로 제작....
 * 하나의 인터페이스...
 * 개발 기간에도 사용 배포에서도 사용
 */
import com.google.code.microlog4android.Level;
import com.logdog.Alarm.LogDogAlarm;
import com.logdog.ErrorReport.ErrorReportFactory;
import com.logdog.Handler.LogDogExceptionHandler;
import com.logdog.Network.LogDogNetwork;
import com.logdog.Process.LogDogProcess;
import com.logdog.Setting.LogDogSetting;
import com.logdog.log.LogDoglog4android;

import android.content.Context;

public final class LogDog {
	
	
	private static LogDog instance;

	public static LogDog getInstance() {
		if (instance == null) {
			synchronized (LogDog.class) { // 1
				if (instance == null) // 2
					instance = new LogDog(); // 3
			}
		}
		return instance;
	}
	
	private LogDogProcess 			Process;
	private LogDogSetting 			Setting;
	private LogDogNetwork 			Network;
	private LogDoglog4android		Log;
	private ErrorReportFactory		Factory;
	private LogDogExceptionHandler	ExceptionHandler;
	private LogDogAlarm				Alarm;

	public LogDog() {
		// TODO Auto-generated constructor stub
		Process 			= null;
		ExceptionHandler 	= null;
		Setting 			= null;
		Log 				= null;
		Factory 			= null;
		Network				= null;
		Alarm				= null;
		
	}
	
	public void LogDoginitialize(Context context,String URL) {
		Alarm			 = new LogDogAlarm(); 
		Setting 		 = new LogDogSetting();
		Network 		 = new LogDogNetwork();
		Log 			 = new LogDoglog4android();
		Factory 		 = new ErrorReportFactory(Setting);
		Process			 = new LogDogProcess(Alarm,Factory,Network,Setting); 
		ExceptionHandler = new LogDogExceptionHandler(Process);
		
		Setting.m_Context = context;
		Log.init(Level.INFO,Setting);
		
	}
	
	void SetLogLever(Level level){
		Log.SetLogLever(level);
	}
		
}
