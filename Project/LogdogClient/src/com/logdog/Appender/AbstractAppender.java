package com.logdog.Appender;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

import com.logdog.ErrorReport.ClientReportData;
import com.logdog.Formatter.IFormatter;
import com.logdog.common.Network.Network;


/**
 *  각 어펜더의 추상 인터페이스
 *  어펜더 생성에 관한 부분에 대해 일괄적으로 처리 하도록 랩퍼 해준 것임.
 *  InitAppender + GetAppender
 * @since 2012. 10. 13.오후 10:09:34
 * TODO 
 * @author JeongSeungsu
 */
@Root(name = "Appender")
public abstract class AbstractAppender 
{
	@Attribute
	String AppenderName;
	
	public AbstractAppender(){
		AppenderName = new String();
	}
	public AbstractAppender(String appendername){
		AppenderName = appendername;
	}
	
	public void SetAppenderName(String name){
		AppenderName = name;
	}
	public String GetAppenderName(){
		return AppenderName;
	}
	
	public boolean ErrorReportProcess(ClientReportData Data){
		return true;
	}
	public abstract void InitAppender(Network network);
	
	public com.google.code.microlog4android.appender.Appender GetAppender(){
		return null;
	}
	
	public abstract String GetClassName();
	
}
