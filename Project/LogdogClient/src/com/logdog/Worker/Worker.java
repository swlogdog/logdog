package com.logdog.Worker;

import android.content.Context;
import android.util.Log;

import com.google.code.microlog4android.Level;
import com.google.code.microlog4android.format.Formatter;
import com.logdog.Appender.AbstractAppender;
import com.logdog.Appender.AppenderConfiguration;
import com.logdog.Appender.AppEngine.AppEngineAppender;
import com.logdog.ErrorReport.ClientReportData;
import com.logdog.Log.LogManager;
import com.logdog.Worker.Factory.AppenderFactory;
import com.logdog.Worker.Factory.ErrorReportFactory;
import com.logdog.Worker.Factory.LogFactory;
import com.logdog.common.Network.Network;
import com.logdog.common.Parser.LogDogXmlParser;


/**
 * 모든 과정을 처리하는 싱글턴 Worker
 * @since 2012. 11. 12.오전 12:23:00
 * TODO
 * @author JeongSeungsu
 */
public class Worker {
	
	private static Worker instance;

	public static Worker getInstance() {
		if (instance == null) {
			synchronized (Worker.class) { 
				if (instance == null){
					instance = new Worker();
					}
				
				}
		}
		return instance;
	}

	
	private static ErrorReportFactory						m_ErrorReportFactory;
	
	private static AppenderConfiguration					m_AppenderConfiguration;
		
	private static Network 									m_Network;
	private static LogManager								m_LogManager;
	
	
	////
	private static Context staticcontext;
	private static String  staticXmlData;
		public Worker() {
		// TODO Auto-generated constructor stub
		
	}
	
	/**
	 * LogDog 초기화
	 * @since 2012. 11. 12.오전 12:23:25
	 * TODO
	 * @author JeongSeungsu
	 * @param context android context
	 * @param XmlData 전체 설정에대한 xml 데이터
	 */
	public void InitLogDogProcess(Context context,String XmlData){
		
		staticcontext = context;
		staticXmlData = XmlData;
		
		m_ErrorReportFactory 	= new ErrorReportFactory(context);
		
		m_Network		 		= new Network(context);
		
		/*
		 *여기서부터 Appender처리 
		 */
		
		String Log = LogDogXmlParser.Separate(XmlData, "Log");
		String Appenders =LogDogXmlParser.Separate(XmlData, "Appenders");
		
		m_AppenderConfiguration = AppenderFactory.CreateAppender(Appenders,m_Network);
		m_LogManager			= LogFactory.CreateLogManager(Log,m_AppenderConfiguration);
	}
	
	/**
	 * 에러 리포트 생성 및 네트워크 전송 서비스 시작
	 * @since 2012. 11. 12.오전 12:24:40
	 * TODO
	 * @author JeongSeungsu
	 * @param throwadata
	 */
	public synchronized void CreateErrorReport(Throwable throwadata){
		m_ErrorReportFactory.CreateErrorReport(m_AppenderConfiguration,throwadata);
		m_Network.SendData();
	}
	
	/**
	 * 앱이 사망할때만 보내는 리포트
	 * @since 2012. 11. 12.오전 2:34:21
	 * TODO
	 * @author JeongSeungsu
	 * @param throwadata
	 * @throws InterruptedException 
	 */
	public void EmergencySendErrorRerport(Throwable throwadata) throws InterruptedException{
		ClientReportData data = m_ErrorReportFactory.CreateErrorReport(m_AppenderConfiguration,throwadata);
		String AppEnginename = AppEngineAppender.class.getName();
		AppEngineAppender app= null;
		for( AbstractAppender appender : m_AppenderConfiguration.getAppenderList()){
			if(appender.getClass().getName().equals(AppEnginename)){
				app = (AppEngineAppender)appender;
				app.GetCommunicator().EmergencySendData(data);
			}
		}
	}

	/**
	 * 로그 레벨 설정
	 * @since 2012. 11. 12.오전 12:24:54
	 * TODO
	 * @author JeongSeungsu
	 * @param level
	 */
	public void SetLogLever(Level level){
		m_LogManager.SetLogLevel(level);
	}
	
	/**
	 * 로그 출력 
	 * 전역 로그 레벨 설정에 의해 출력될지 안될지 결정
	 * @since 2012. 11. 12.오전 12:24:59
	 * TODO
	 * @author JeongSeungsu
	 * @param level 이 레벨값에 따라 출력되는 로그 
	 * @param log 출력할 String 데이터
	 */
	public void PrintLog(Level level,String log){
		m_LogManager.PrintLog(level, log);
	}
	
	/**
	 * Exception을 받아서 로그 출력
	 * 그리고 보낼 것이 있으면 쓰레드를 생성해서 보낸다.
	 * Appender에 따라 보낼 행동이 달라짐
	 * @since 2012. 11. 12.오전 12:25:48
	 * TODO
	 * @author JeongSeungsu
	 * @param level 보일 레벨
	 * @param t Exception 데이터
	 */
	public void PrintLog(Level level,Throwable t){
		m_LogManager.PrintLog(level, t);
		//CreateErrorReport(t);
		ErrorReportProcess process = new ErrorReportProcess(t);
		process.start();
	}
	class ErrorReportProcess extends Thread{
		Throwable throwable;
		public ErrorReportProcess(Throwable t){
			throwable = t;
		}
		@Override
		public void run(){
			Worker.getInstance().CreateErrorReport(throwable);
		}
	}
	
	/**
	 * 어펜더 추가
	 * @since 2012. 11. 12.오전 12:26:08
	 * TODO
	 * @author JeongSeungsu
	 * @param appender
	 */
	public void AddAppender(AbstractAppender appender){
		m_AppenderConfiguration.AddAppender(appender);
		m_LogManager.AddAppender(appender);
	}
	
	/**
	 * 어펜더 삭제
	 * 어펜더 이름에 의한 삭제
	 * @since 2012. 11. 12.오전 12:26:45
	 * TODO
	 * @author JeongSeungsu
	 * @param AppenderName xml에 설정한 Name에 의해 삭제 된다.
	 * @return 
	 */
	public void RemoveAppender(String AppenderName){
		m_AppenderConfiguration.RemoveAppender(AppenderName);
		m_LogManager.RemoveAppender(m_AppenderConfiguration.GetAppender(AppenderName));
	}
	/**
	 * 어펜더 삭제
	 * @since 2012. 11. 13.오전 3:38:50
	 * TODO
	 * @author JeongSeungsu
	 * @param appender
	 */
	public void RemoveAppender(AbstractAppender appender){
		m_AppenderConfiguration.RemoveAppender(appender);
		m_LogManager.RemoveAppender(appender);
	}
}
