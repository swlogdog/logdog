package com.logdog.Setting;

/**
 * 로그도그의 총체적인 셋팅값을 모아 둔것...
 * @since 2012. 10. 27.오전 4:32:32
 * TODO
 * @author JeongSeungsu
 */
public class LogDogSetting {

	private String 						m_LogFileName;
	private static String				m_LogFileExtension = ".log";
	private LogDogServerSetting 		m_ServerSetting; 
	
	
	public LogDogSetting() {
		// TODO Auto-generated constructor stub
	}

	public void SetLogFileName(String name){
		if(name == null)
			m_LogFileName = "logdog" + m_LogFileExtension;
		m_LogFileName = name+m_LogFileExtension;
	}
	
	public String GetLogFileName(){
		return m_LogFileName;
	}
}
