package com.logdog.Worker.Factory;


import com.logdog.Appender.AppenderConfiguration;
import com.logdog.Worker.Log.LogConfiguration;
import com.logdog.Worker.Log.LogManager;
import com.logdog.common.Parser.LogDogXmlParser;

public class LogFactory {

	private static LogConfiguration createLogConfiguration(String Xml){
		LogConfiguration conf = null;
		try{
			conf = (LogConfiguration) LogDogXmlParser.fromXml(LogConfiguration.class, Xml);
		}catch(Exception e){
			e.printStackTrace();
		}
		return conf;
	}
	public static LogManager CreateLogManager(String Xml,AppenderConfiguration appenderconf){
		LogConfiguration conf = createLogConfiguration(Xml);
		LogManager manager = new LogManager();
		conf.ConverterStringtoLevel();
		manager.SetLogLevel(conf.GetLevel());
		manager.InitLog(appenderconf);
		return manager;
	}
}
