package com.logdog.Worker;

import android.content.Context;

import com.google.code.microlog4android.Level;
import com.google.code.microlog4android.format.Formatter;
import com.logdog.Appender.AbstractAppender;
import com.logdog.Appender.AppenderConfiguration;
import com.logdog.Worker.Factory.AppenderFactory;
import com.logdog.Worker.Factory.ErrorReportFactory;
import com.logdog.Worker.Factory.LogFactory;
import com.logdog.Worker.Log.LogManager;
import com.logdog.common.Network.Network;
import com.logdog.common.Parser.LogDogXmlParser;


public class Worker {
	
	private static Worker instance;

	public static Worker getInstance() {
		if (instance == null) {
			synchronized (Worker.class) { 
				if (instance == null) 
					instance = new Worker(); 
			}
		}
		return instance;
	}

	
	private ErrorReportFactory						m_ErrorReportFactory;
	
	private AppenderConfiguration					m_AppenderConfiguration;
		
	private Network 								m_Network;
	private LogManager								m_LogManager;
	
	
	public Worker() {
		// TODO Auto-generated constructor stub
		
	}
	
	public void InitLogDogProcess(Context context,String XmlData){
		
		
		m_ErrorReportFactory 	= new ErrorReportFactory(context);
		
		m_Network		 		= new Network(context);
		
		/*
		 *여기서부터 Appender처리 
		 */
		
		String Log = LogDogXmlParser.Separate(XmlData, "Log");
		String Appenders =LogDogXmlParser.Separate(XmlData, "Appenders");
		
		m_AppenderConfiguration = AppenderFactory.CreateAppender(Appenders);
		InitAppenders(m_AppenderConfiguration,m_Network);
		
		m_LogManager			= LogFactory.CreateLogManager(Log,m_AppenderConfiguration);
	}
	private void InitAppenders(AppenderConfiguration configuration,Network network){
		for(AbstractAppender appender : configuration.getAppenderList()){
			appender.InitAppender(network);
		}
	}
	
	
	
	/**
	 * Service에서 콜하는 함수...
	 * @since 2012. 11. 10.오전 1:35:54
	 * TODO
	 * @author JeongSeungsu
	 * @return
	 */
	public boolean SendErrorReport(){
		return m_Network.SendData();
	}
	
	
	public void CreateErrorReport(Throwable throwadata){
		m_ErrorReportFactory.CreateErrorReport(m_AppenderConfiguration,throwadata);
		m_Network.StartService();
	}

	public void SetLogLever(Level level){
		m_LogManager.SetLogLevel(level);
	}
	public void PrintLog(Level level,String log){
		m_LogManager.PrintLog(level, log);
	}
	
	public void PrintLog(Level level,Throwable t){
		m_LogManager.PrintLog(level, t);
		CreateErrorReport(t);
	}
	public void SetFormatter(Formatter formatter){
		m_LogManager.SetFormatter(formatter);
	}
	
	public void AddAppender(AbstractAppender appender){
		m_AppenderConfiguration.AddAppender(appender);
	}
	
	public boolean DeleteAppender(String AppenderName){
		return m_AppenderConfiguration.DeleteAppender(AppenderName);
	}
}
