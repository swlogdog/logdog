package com.logdog;
/*
 * 더블 체크드 락킹....
 * 팩토리로 만들어서 사용자가 팩토리를 사용하도록 
 * 4개의 레벨, 어펜더, 포매터, 리포트 
 * 서비스 형태로 제작....
 * 하나의 인터페이스...
 * 개발 기간에도 사용 배포에서도 사용
 */
import java.io.File;

import com.google.code.microlog4android.Level;
import com.google.code.microlog4android.format.Formatter;


import com.logdog.Handler.LogDogExceptionHandler;
import com.logdog.Process.LogDogProcess;
import com.logdog.log.LogDoglog4android;


import android.content.Context;

public final class LogDog {
	
	

	private static LogDogExceptionHandler	ExceptionHandler;
	private static LogDoglog4android		Logger;
	


	public LogDog() {
		// TODO Auto-generated constructor stub	
	}
	
	public static void LogDoginitialize(Context context,String NetworkXml) { 
		LogDogProcess.getInstance().InitLogDogProcess(context,NetworkXml);
		ExceptionHandler = new LogDogExceptionHandler(LogDogProcess.getInstance());
		Logger = new LogDoglog4android();
		Logger.init(Level.DEBUG, LogDogProcess.getInstance().GetSetting());
	}
	
	public static void SetLogLever(Level level){
		Logger.SetLogLever(level);
	}
	public static void PrintLog(Level level,String log){
		Logger.PrintLog(level, log);
	}
	public static void SetFormatter(Formatter formatter){
		Logger.SetFormatter(formatter);
	}
	
	public static boolean SendingErrorReport(){
		if(LogDogProcess.getInstance().SendErrorReport())
			return true;
		else
			return false;
	}
	public static void CreateLog(Throwable throwdata){
		LogDogProcess.getInstance().CreateErrorReport(throwdata);
	}
		
}
