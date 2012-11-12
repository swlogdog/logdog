package com.logdog.Worker.Factory;


import com.logdog.Appender.AppenderConfiguration;
import com.logdog.Log.LogConfiguration;
import com.logdog.Log.LogManager;
import com.logdog.common.Parser.LogDogXmlParser;

/**
 * xml 데이터로 LogManager 생성하는 팩토리 
 * @since 2012. 11. 12.오전 12:20:38
 * TODO
 * @author JeongSeungsu
 */
public class LogFactory {

	/**
	 * Xml Data에 의해 LogConfiguration을 만들어 낸다.
	 * @since 2012. 11. 12.오전 12:21:00
	 * TODO
	 * @author JeongSeungsu
	 * @param Xml
	 * @return
	 */
	private static LogConfiguration createLogConfiguration(String Xml){
		LogConfiguration conf = null;
		try{
			conf = (LogConfiguration) LogDogXmlParser.fromXml(LogConfiguration.class, Xml);
		}catch(Exception e){
			e.printStackTrace();
		}
		return conf;
	}
	/**
	 * log xml 데이터 및 AppenderConfiguration에 의해 LogManager를 생성한다.
	 * @since 2012. 11. 12.오전 12:21:14
	 * TODO
	 * @author JeongSeungsu
	 * @param Xml XML 데이터
	 * @param appenderconf LogManager를 생성할때는 AppenderConfiguration이 필요하다
	 * @return LogManager 객체
	 */
	public static LogManager CreateLogManager(String Xml,AppenderConfiguration appenderconf){
		LogConfiguration conf = createLogConfiguration(Xml);
		LogManager manager = new LogManager();
		conf.ConverterStringtoLevel();
		manager.SetLogLevel(conf.GetLevel());
		manager.InitLog(appenderconf);
		return manager;
	}
}
