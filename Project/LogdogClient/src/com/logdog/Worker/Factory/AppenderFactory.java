package com.logdog.Worker.Factory;

import com.logdog.Appender.AppenderConfiguration;
import com.logdog.common.Parser.LogDogXmlParser;

/**
 * AppenderConfiguration을 생성하는 팩토리
 * @since 2012. 11. 12.오전 12:19:04
 * TODO
 * @author JeongSeungsu
 */
public class AppenderFactory {

	/**
	 * AppenderConfiguration을 Xml데이터에 의해 생성한다.
	 * @since 2012. 11. 12.오전 12:19:28
	 * TODO
	 * @author JeongSeungsu
	 * @param Xml Xml String 데이터
	 * @return AppenderConfiguration 어펜더 설정 값들 
	 */
	public static AppenderConfiguration CreateAppender(String Xml){
		AppenderConfiguration conf = null;
		try{
			conf = (AppenderConfiguration) LogDogXmlParser.fromXml(AppenderConfiguration.class, Xml);
		}catch(Exception e){
			e.printStackTrace();
		}
		return conf;
	}
	
}
