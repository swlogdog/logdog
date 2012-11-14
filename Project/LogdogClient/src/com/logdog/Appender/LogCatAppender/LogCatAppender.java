package com.logdog.Appender.LogCatAppender;

import java.util.Map;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

import com.google.code.microlog4android.format.PatternFormatter;
import com.logdog.Appender.AbstractAppender;
import com.logdog.ErrorReport.ClientReportData;
import com.logdog.Formatter.IFormatter;
import com.logdog.common.Network.Network;


/**
 * 로그켓에 로그 적용
 * @since 2012. 10. 13.오후 10:11:05
 * TODO 
 * @author JeongSeungsu
 */
@Root
public class LogCatAppender extends AbstractAppender {

	com.google.code.microlog4android.appender.LogCatAppender appender;
	
	@Element
	IFormatter Formatter;
	
	/**
	 *
	 * @since 2012. 11. 15.오전 5:57:50
	 * TODO
	 * @author JeongSeungsu
	 */
	public LogCatAppender(){
		super();
	}
	/**
	 *
	 * @since 2012. 11. 15.오전 5:57:49
	 * TODO
	 * @author JeongSeungsu
	 * @param AppenderName
	 * @param formatter
	 */
	public LogCatAppender(String AppenderName,IFormatter formatter){
		super(AppenderName);
		Formatter = formatter;
	}

	public boolean ErrorReportProcess(ClientReportData Data) {
		// TODO Auto-generated method stub
		
		return false;
	}
	public void InitAppender(Network network) {
		// TODO Auto-generated method stub
		appender = new com.google.code.microlog4android.appender.LogCatAppender();
		
		Formatter.InitFormatter();
		appender.setFormatter(Formatter.GetLog4Formatter());
	}

	public com.google.code.microlog4android.appender.Appender GetLog4Appender() {
		// TODO Auto-generated method stub
		return appender;
	}


	
	
}
