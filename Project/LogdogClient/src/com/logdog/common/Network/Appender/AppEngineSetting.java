package com.logdog.common.Network.Appender;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

import com.logdog.common.Network.NetwrokSetting;

@Root
public class AppEngineSetting implements NetwrokSetting {

	@Element
	private String URL;
	
	private final String ErrorCheckUrl = "ErrorType";
	
	private final String SendUserInfoUrl = "UserInfo";
	
	private final String LogSettingUrl = "LogSetting";
	
	
	public AppEngineSetting() {
		// TODO Auto-generated constructor stub
	}

	public void SetURL(String url){ 
		URL = url;
	}
	public String GetURL(){
		return URL;
	}
	
	public String GetErrorCheckUrl(){
		return ErrorCheckUrl;
	}
	public String GetSendUserInfoUrl(){
		return SendUserInfoUrl;
	}
	public String GetLogSettingUrl(){
		return LogSettingUrl;
	}

	public String GetAppenderClassName() {
		return "com.logdog.common.Network.Appender.AppEngineAppender";
	}

	public Class GetSettingClass() {
		// TODO Auto-generated method stub
		return AppEngineSetting.class;
	}
}
