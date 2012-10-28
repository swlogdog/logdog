package com.logdog.ErrorReport;

public class ErrorReportFactory {

	public ErrorReportFactory() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 *
	 * warning!! 리포트 생성시 꼭 Date의 값을 먼저 설정해준뒤 Collector들을 실행해야함..
	 * @since 2012. 10. 29.오전 5:39:07
	 * TODO
	 * @author JeongSeungsu
	 * @return
	 */
	public ErrorReportData CreateErrorReport(){
		ErrorReportData data = null;
		data.Date = null;
		
		return data; 
	}

}
