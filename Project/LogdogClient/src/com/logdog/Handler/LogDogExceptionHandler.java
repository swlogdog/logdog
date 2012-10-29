package com.logdog.Handler;

import com.logdog.ErrorReport.Collector.StackTraceCollector;
import com.logdog.ErrorReport.ReportData.ClientReportData;
import com.logdog.Process.LogDogProcess;

import android.content.Context;
import android.os.Process;

public class LogDogExceptionHandler implements Thread.UncaughtExceptionHandler{

	private LogDogProcess Process; 
	Thread.UncaughtExceptionHandler m_DefaultExceptionHandler;
	
	
	public LogDogExceptionHandler(LogDogProcess process) {
		// TODO Auto-generated constructor stub
		m_DefaultExceptionHandler = Thread.getDefaultUncaughtExceptionHandler();
        Thread.setDefaultUncaughtExceptionHandler(this);
        Process = process;
        
	}

	public void uncaughtException(Thread thread, Throwable ex) {
		// TODO Auto-generated method stub
	
		
		
		
		
		m_DefaultExceptionHandler.uncaughtException(thread, ex);
		/*
		android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(10);
        */

	}

}
