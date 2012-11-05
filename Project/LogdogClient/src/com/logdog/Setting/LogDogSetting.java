package com.logdog.Setting;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

import android.content.Context;

/**
 * 로그도그의 총체적인 셋팅값을 모아 둔것...
 * @since 2012. 10. 27.오전 4:32:32
 * TODO
 * @author JeongSeungsu
 */
/*
 * @Attribute(required=false)
 * (name="")
 *  @ElementList
 *   @ElementArray
 *   inline=true
 *   @ElementMap(entry="property", key="key", attribute=true, inline=true)
 */
@Root
public final class LogDogSetting {

	/////////////////////Log 관련 셋팅/////////////////////////////
	/**
	 * 디폴트값 100 , 0이면 모든 로그 기록
	 */
	@Element(name="")
	private int							m_ReadLogLine;			
	 
	private boolean 					m_SendLog;
	
	private final String				m_LogAppenderName 	= "Log4LogCat|Log4File";
	
	private final String 				m_LogFileName		= "LogDog.txt";
	
	
	/////////////////Network 관련 셋팅/////////////////////////////
	
	private String						m_URL;
	
	
	
	////////////////ErrorReport 관련 셋팅//////////////////
	private final String				m_StackTraceName 	= "StackTrace.txt";
	
	
	
	/////////////////XML 안씀/////////////////////////////
	private String						m_FileSaveDirectory;
	
	public Context						m_Context;

	public LogDogSetting() {
		// TODO Auto-generated constructor stub
		m_URL 		= null;
		m_SendLog 	= false;
		
		m_FileSaveDirectory = "LogDog";
		m_ReadLogLine		= 100;
		
	}
	
	public Boolean GetReadLog(){
		return m_SendLog;
	}
	public void SetReadLog(Boolean log){
		m_SendLog = log;
	}
	public String GetStackTraceFileName(){
		return m_StackTraceName;
	}
	public void SetReadLogLine(int line){
		m_ReadLogLine = line;
	}
	public int GetReadLogLine(){
		return m_ReadLogLine;
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
