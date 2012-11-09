
package com.logdog.Worker.Log;

import com.google.code.microlog4android.Level;
import com.google.code.microlog4android.Logger;
import com.google.code.microlog4android.LoggerFactory;
import com.google.code.microlog4android.format.Formatter;

import com.logdog.Appender.*;



/**
 * 로그4안드로이드 랩퍼 클래스
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
	 * 로그 남기는 클래스 초기화
	 * appender를 선택하여 필요한것과 보여줄 로그의 레벨 설정 
	 * @since 2012. 10. 13.오후 10:02:25
	 * TODO 
	 * @author JeongSeungsu
	 * @param appendernames 쓸 어펜더 이름 "Log4LogCat|Log4File" 예제 중간에 | 넣어줘서 분리함
	 * @param loglevel 레벨 설정 debug, info, fetal, error, warn 설정 가능
	 
	 */
/*	public void init(Level loglevel)
	{
		try {
			PatternFormatter formatter = new PatternFormatter();     //포맷터 설정 부분 변경 필요...
			formatter.setPattern("   %d{ISO8601}    [%P]  %m  %T  ");
			m_logger.setLevel(loglevel);

			String[] StrArray;
			StrArray = setting.GetLogAppenderName().split("\\|");

			for (String s : StrArray) {

				com.google.code.microlog4android.appender.Appender appender = InitAppender(s);

				appender.setFormatter(formatter);
				m_logger.addAppender(appender);
			}
			
			m_logger.info("LogDog log Init");
		} catch (Exception e) { 
			Log.e("LOG_ERROR", "FAIL Log4Andorid : " );
		}
	}
*/

	/**
	 *
	 * @since 2012. 11. 10.오전 12:59:24
	 * TODO Formatter 추가해야됨
	 * @author JeongSeungsu
	 * @param appender
	 */
	public void AddAppender(AbstractAppender appender){
		com.google.code.microlog4android.appender.Appender Appender = appender.GetAppender();
		if(Appender== null)
			return;
		
		m_logger.addAppender(Appender);
		//ForMatter추가
	}
	public void InitLog(AppenderConfiguration configuration){
		for(AbstractAppender appender : configuration.getAppenderList()){
			AddAppender(appender);
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
