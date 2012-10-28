package com.logdog.Setting;

import android.content.Context;

/**
 * 로그도그의 총체적인 셋팅값을 모아 둔것...
 * @since 2012. 10. 27.오전 4:32:32
 * TODO
 * @author JeongSeungsu
 */
public final class LogDogSetting {

	
	private final String				m_StackTraceName 	= "StackTrace.txt";
	 
	
	private final String				m_LogAppenderName 	= "Log4LogCat|Log4File";
	private final String 				m_LogFileName		= "LogDog.txt";
	
	
	private String						m_URL;
	private LogDogServerSetting 		m_ServerSetting;
	
							
	
	private String						m_FileSaveDirectory;
	
	public Context						m_Context;
	
	
	
	public LogDogSetting() {
		// TODO Auto-generated constructor stub
	}

	public void SetSaveDirPath(String dir){
		m_FileSaveDirectory = dir;
	}
	public String GetSaveDirPath(){
		return m_FileSaveDirectory;
	}
	
	public String GetLogAppenderName(){
		return m_LogAppenderName;
	}
	public String GetLogFileName(){
		return m_LogFileName;
	}
	public void SetURL(String url){
		m_URL = url;
	}
	public String GetURL(){
		return m_URL;
	}
}
