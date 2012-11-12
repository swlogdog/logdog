package com.logdog.ErrorReport;

/**
 * 서버나 로컬에 저장할 ErrorReport
 * @since 2012. 11. 12.오전 12:15:06
 * TODO
 * @author JeongSeungsu
 */
public class ClientReportData {

	public ClientReportData() {
		// TODO Auto-generated constructor stub
		ReportTime 			= "unknown";
		Model 				= "unknown";
		National 			= "unknown";
		ErrorName			= "";
		ErrorClassName		= "";
		line				= -1;
		AppVersion			= "unknown";
		OSVersion			= "unknown";
		GPS					= false;
		WiFi				= false;
		MobileNetwork		= false;
		ScreenWidth			= -1;
		ScreenHeight		= -1;
		CallStackFileName 	= null;
		LogFileName			= null;
	}
	/**
	 * 에러 발생 시간
	 */
	public String ReportTime;
	/**
	 * 핸드폰 모델 명
	 */
	public String Model;
	/**
	 * 국가명
	 */
	public String National;
	/**
	 * 에러 이름
	 */
	public String ErrorName;
	/**
	 * 에러가 발생한 클래스 이름
	 * ClassName
	 * 클래스 이름
	 */
	public String ErrorClassName;
	/**
	 * Error Code Line 
	 */
	public int line;
	/**
	 * 앱 버젼
	 */
	public String AppVersion;
	/**
	 * OS 버젼
	 */
	public String OSVersion;
	//public String CallStack;
	//public String UserLog;
	
	/**
	 * GPS On/Off
	 */
	public boolean GPS;
	/**
	 * WiFi On/Off
	 */
	public boolean WiFi;
	/**
	 * MobileNetwork(3G) On/Off
	 */
	public boolean MobileNetwork;
	/**
	 * 화면 가로 크기
	 */
	public int ScreenWidth;
	/**
	 * 화면 세로 크기
	 */
	public int ScreenHeight;
	
	/**
	 * 보낼 콜스택 파일 이름
	 */
	public String CallStackFileName;
	
	/**
	 * 보낼 로그 파일 이름
	 */
	public String LogFileName;
}
