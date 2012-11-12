
package com.logdog.Log;

import com.google.code.microlog4android.Level;
import com.google.code.microlog4android.Logger;
import com.google.code.microlog4android.LoggerFactory;
import com.google.code.microlog4android.format.Formatter;

import com.logdog.Appender.*;



/**
 * 로그 관리하는 매니져 클래스
 * 랩퍼 클래스로 간단한 꼭 필요한 인터페이스만 제공한다.
 * @since 2012. 10. 13.오후 9:44:41
 * TODO 
 * @author JeongSeungsu
 */
public class LogManager {

	
	/**
	 *  Logger 객체 microLog4andorid 중요 객체
	 */
	private Logger m_logger; 
	
	
	public LogManager() {
		// TODO Auto-generated constructor stub		
		m_logger = LoggerFactory.getLogger();

	}
		
	/**
	 * 어펜더 추가
	 * @since 2012. 11. 10.오전 12:59:24
	 * TODO 
	 * @author JeongSeungsu
	 * @param appender
	 */
	public void AddAppender(AbstractAppender appender){
		com.google.code.microlog4android.appender.Appender Appender = appender.GetLog4Appender();
		if(Appender== null)
			return;
		
		m_logger.addAppender(Appender);
		//ForMatter추가
	}
	
	/**
	 * 어펜더 연결해제
	 * @since 2012. 11. 13.오전 3:23:52
	 * TODO
	 * @author JeongSeungsu
	 * @param appender
	 */
	public boolean RemoveAppender(AbstractAppender appender){
		if(appender == null)
			return false;
		com.google.code.microlog4android.appender.Appender Appender = appender.GetLog4Appender();
		if(Appender== null)
			return false;
		
		m_logger.removeAppender(Appender);
		return true;
	}
	
	/**
	 * 모든 들어 있는 어펜더 연결해제
	 * @since 2012. 11. 13.오전 3:24:25
	 * TODO
	 * @author JeongSeungsu
	 */
	public void ClearAppenders(){
		m_logger.removeAllAppenders();
	}
	
	/**
	 * AppenderConfiguration에 의한 초기화 
	 * Appender들을 로거 객체에 넣어준다.
	 * @since 2012. 11. 13.오전 3:21:38
	 * TODO
	 * @author JeongSeungsu
	 * @param configuration
	 */
	public void InitLog(AppenderConfiguration configuration){
		for(AbstractAppender appender : configuration.getAppenderList()){
			AddAppender(appender);
		}
	}
	
	/**
	 * 로그 출력
	 * String 데이터를 출력한다.
	 * @since 2012. 11. 13.오전 3:25:28
	 * TODO
	 * @author JeongSeungsu
	 * @param level 설정할 Level 값
	 * @param log 출력할 String 데이터
	 */
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
	 * Exception 데이터를 출력한다.
	 * @since 2012. 11. 13.오전 3:25:58
	 * TODO
	 * @author JeongSeungsu
	 * @param level 출력할 레벨값 설정
	 * @param t 출력할 Exception데이터
	 */
	public void PrintLog(Level level, Throwable t){
	
		switch(level.toInt()){
		case Level.DEBUG_INT:
			m_logger.debug("Exception",t);
			break;
		case Level.ERROR_INT:
			m_logger.error("Exception",t);
			break;
		case Level.FATAL_INT:
			m_logger.fatal("Exception",t);
			break;
		case Level.INFO_INT:
			m_logger.info("Exception",t);
			break;
		case Level.WARN_INT:
			m_logger.warn("Exception",t);
			break;
		}
	}
	
	/**
	 * 로그 레벨 설정
	 * @since 2012. 10. 28.오후 8:49:17
	 * TODO
	 * @author JeongSeungsu
	 * @param level 
	 */
	public void SetLogLevel(Level level){
		m_logger.setLevel(level);
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
