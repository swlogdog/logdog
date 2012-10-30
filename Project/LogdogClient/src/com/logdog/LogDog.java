package com.logdog;

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
	
	void LogDoginitialize(Context context,String URL) {
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
