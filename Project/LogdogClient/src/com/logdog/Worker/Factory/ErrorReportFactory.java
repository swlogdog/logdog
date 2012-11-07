package com.logdog.Worker.Factory;

import com.logdog.Configuration.LogDogSetting;
import com.logdog.ErrorReport.ClientReportData;
import com.logdog.ErrorReport.Collector.LogCollector;
import com.logdog.ErrorReport.Collector.MemoryCollector;
import com.logdog.ErrorReport.Collector.StackTraceCollector;
import com.logdog.ErrorReport.Collector.SystemInfoCollector;
import com.logdog.common.Date.Date;

public class ErrorReportFactory {

	LogCollector 			m_LogCollector;
	MemoryCollector			m_MemoryCollector;
	StackTraceCollector		m_StackCollector;
	SystemInfoCollector		m_SysInfoCollector;
	
	LogDogSetting			Setting;
	
	public ErrorReportFactory(LogDogSetting setting) {
		// TODO Auto-generated constructor stub
		Setting = setting;
		
		m_LogCollector 		= new LogCollector(Setting);
		m_StackCollector	= new StackTraceCollector(Setting);
		m_SysInfoCollector	= new SystemInfoCollector(Setting);
		//m_MemoryCollector	= new MemoryCollector();
		
	}
	
	/**
	 *
	 * warning!! 리포트 생성시 꼭 Date의 값을 먼저 설정해준뒤 Collector들을 실행해야함..
	 * @since 2012. 10. 29.오전 5:39:07
	 * TODO
	 * @author JeongSeungsu
	 * @return
	 */
	public ClientReportData CreateErrorReport(Throwable errorthorw){
		ClientReportData data = new ClientReportData();
		
		data.ReportTime = Date.GetDateYYMMDDHHMMSS(Setting.m_Context);
		
		m_StackCollector.DoCollectStackTrace(data, errorthorw);
		m_SysInfoCollector.DoCollectSystemInfo(data);
		if(Setting.GetReadLog())
			m_LogCollector.DoCollectLog(data);
		//m_MemoryCollector.DoCollect(data);
		return data; 
	}

}
