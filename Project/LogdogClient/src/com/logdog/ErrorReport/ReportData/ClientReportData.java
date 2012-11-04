package com.logdog.ErrorReport.ReportData;

public class ClientReportData {

	public ClientReportData() {
		// TODO Auto-generated constructor stub
		ReportTime 			= "unknown";
		Model 				= "unknown";
		National 			= "unknown";
		ErrorName			= "";
		ErrorClassName		= "";
		AppVersion			= "unknown";
		OSVersion			= "unknown";
		ErrorLine			= -1;
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
	 * ClassName(60)
	 * 클래스 이름+(라인)
	 */
	public String ErrorClassName;
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
	 * 에러가 발생 한 라인
	 */
	public int ErrorLine;
	
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
