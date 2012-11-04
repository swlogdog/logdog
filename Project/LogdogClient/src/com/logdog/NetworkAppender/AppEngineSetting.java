package com.logdog.NetworkAppender;

public class AppEngineSetting {

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
}
