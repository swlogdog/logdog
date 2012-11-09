package com.logdog.Handler;


import com.logdog.Worker.Worker;



public class ExceptionHandler implements Thread.UncaughtExceptionHandler{

	private Worker Process; 
	Thread.UncaughtExceptionHandler m_DefaultExceptionHandler;
	
	
	public ExceptionHandler(Worker process) {
		// TODO Auto-generated constructor stub
		m_DefaultExceptionHandler = Thread.getDefaultUncaughtExceptionHandler();
        Thread.setDefaultUncaughtExceptionHandler(this);
        Process = process;
        
	}

	public void uncaughtException(Thread thread, Throwable ex) {
		// TODO Auto-generated method stub
	
		Process.CreateErrorReport(ex);
		m_DefaultExceptionHandler.uncaughtException(thread, ex);
		/*
		android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(10);
        */

	}

	@Override
	protected void finalize() throws Throwable {
		// TODO Auto-generated method stub
		super.finalize();
	}

}
