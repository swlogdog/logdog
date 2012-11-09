package com.logdog.Worker.Factory;

import com.logdog.Appender.AppenderConfiguration;
import com.logdog.common.Parser.LogDogXmlParser;

public class AppenderFactory {

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
