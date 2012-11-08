package com.logdog.Worker.Factory;

import com.logdog.Appender.AppenderManager;
import com.logdog.Appender.IAppender;
import com.logdog.Configuration.LogDogConfiguration;
import com.logdog.ErrorReport.ClientReportData;
import com.logdog.ErrorReport.Collector.MemoryCollector;
import com.logdog.ErrorReport.Collector.StackTraceCollector;
import com.logdog.ErrorReport.Collector.SystemInfoCollector;
import com.logdog.common.Date.Date;

public class ErrorReportFactory {

	MemoryCollector			m_MemoryCollector;
	StackTraceCollector		m_StackCollector;
	SystemInfoCollector		m_SysInfoCollector;
	
	LogDogConfiguration			Setting;
	
	public ErrorReportFactory(LogDogConfiguration setting) {
		// TODO Auto-generated constructor stub
		Setting = setting;
		
		
		m_StackCollector	= new StackTraceCollector();
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
	public ClientReportData CreateErrorReport(AppenderManager appendermanager,Throwable errorthorw){
		ClientReportData data = new ClientReportData();
		
		data.ReportTime = Date.GetDateYYMMDDHHMMSS(Setting.m_Context);
		
		m_StackCollector.DoCollectStackTrace(data, errorthorw);
		m_SysInfoCollector.DoCollectSystemInfo(data);
		//m_MemoryCollector.DoCollect(data);
		
		for(IAppender appender : appendermanager.getAppenderList()){
			appender.ErrorReportProcess(data);
		}
		return data; 
	}

}
