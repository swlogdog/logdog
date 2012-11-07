
package com.logdog;

import android.util.Log;

import com.google.code.microlog4android.Level;
import com.google.code.microlog4android.Logger;
import com.google.code.microlog4android.LoggerFactory;
import com.google.code.microlog4android.format.Formatter;
import com.google.code.microlog4android.format.PatternFormatter;

import com.logdog.Appender.*;
import com.logdog.Configuration.LogDogSetting;



/**
 * 로그4안드로이드 랩퍼 클래스
 * 랩퍼 클래스로 간단한 꼭 필요한 인터페이스만 제공한다.
 * @since 2012. 10. 13.오후 9:44:41
 * TODO 
 * @author JeongSeungsu
 */
public class LogDoglog4android {

	
	/**
	 *  Logger 객체 microLog4andorid 중요 객체
	 */
	private Logger m_logger; 
	
	
	public LogDoglog4android() {
		// TODO Auto-generated constructor stub		
		m_logger = LoggerFactory.getLogger();

	}
		
	/**
	 * 로그 남기는 클래스 초기화
	 * appender를 선택하여 필요한것과 보여줄 로그의 레벨 설정 
	 * @since 2012. 10. 13.오후 10:02:25
	 * TODO 
	 * @author JeongSeungsu
	 * @param appendernames 쓸 어펜더 이름 "Log4LogCat|Log4File" 예제 중간에 | 넣어줘서 분리함
	 * @param loglevel 레벨 설정 debug, info, fetal, error, warn 설정 가능
	 
	 */
	public void init(Level loglevel,LogDogSetting setting)
	{
		try {
			PatternFormatter formatter = new PatternFormatter();     //포맷터 설정 부분 변경 필요...
			formatter.setPattern("   %d{ISO8601}    [%P]  %m  %T  ");
			m_logger.setLevel(loglevel);

			String[] StrArray;
			StrArray = setting.GetLogAppenderName().split("\\|");

			for (String s : StrArray) {

				com.google.code.microlog4android.appender.Appender appender = InitAppender(s,setting);

				appender.setFormatter(formatter);
				m_logger.addAppender(appender);
			}
			
			m_logger.info("LogDog log Init");
		} catch (Exception e) { 
			Log.e("LOG_ERROR", "FAIL Log4Andorid : " + setting.GetLogAppenderName());
		}
	}
	
	public void PrintLog(Level level, String log){
		
		switch(level.toInt()){
		case Level.DEBUG_INT:
			m_logger.debug(log);
			break;
		case Level.ERROR_INT:
			m_logger.error(log);
			break;
		case Level.FATAL_INT:
			m_logger.fatal(log);
			break;
		case Level.INFO_INT:
			m_logger.info(log);
			break;
		case Level.WARN_INT:
			m_logger.warn(log);
			break;
		}
	}
	
	/**
	 * 포맷터 설정하는데 모든 어펜더에 동일하게...
	 * 각 어펜더 마다 포매터 설정
	 * @since 2012. 10. 13.오후 10:08:20
	 * TODO 
	 * @author JeongSeungsu
	 * @param formatter
	 */
	public void SetFormatter(Formatter formatter){
		for(int i =0 ; i < m_logger.getNumberOfAppenders(); i++)
		{
			m_logger.getAppender(i).setFormatter(formatter);
		}
	}
	
	/**
	 * 로그 레벨 설정
	 * @since 2012. 10. 28.오후 8:49:17
	 * TODO
	 * @author JeongSeungsu
	 * @param level 
	 */
	public void SetLogLever(Level level){
		m_logger.setLevel(level);
	}
	
	/**
	 * 어펜더 스트링을 이용하여 각 어펜더들을 초기화
	 * @since 2012. 10. 13.오후 10:08:56
	 * TODO 
	 * @author JeongSeungsu
	 * @param appendername
	 * @return
	 */
	private com.google.code.microlog4android.appender.Appender InitAppender(String appendername,LogDogSetting setting) {

		Appender appender = null;
		String packagename;

		try {
			
			packagename = Appender.class.getName();
			packagename = packagename.replace(".Log4Appender", ""); 
			appendername = packagename + "." + appendername;
			Class c = Class.forName(appendername);
			appender = (Appender) c.newInstance();
			appender.CreateAppender(setting);

		} catch (ClassNotFoundException e1) { //클래스 이름이 없다.
			Log.e("LOGDOG", "Class is Not Found");
			return null;
		} catch (InstantiationException e2) { //인스턴스를 생성 할 수 없다.
			Log.e("LOGDOG", "new Instance Fail");
			return null;
		} catch (IllegalAccessException e3) { //클래스 파일을 액세서 할 수 없다.
			Log.e("LOGDOG", "Class File Access Error");
			return null;
		}

		return (com.google.code.microlog4android.appender.Appender) appender.GetAppender();
	}

	public void warn(String log){
		m_logger.warn(log);
	}
	public void info(String log){
		m_logger.info(log);
	}
	public void fatal(String log){
		m_logger.fatal(log);
	}
	public void error(String log){
		m_logger.error(log);
	}
	public void debug(String log){
		m_logger.debug(log);
	}

}
