package com.logdog.Process;

import com.google.gson.Gson;
import com.logdog.Alarm.LogDogAlarm;
import com.logdog.ErrorReport.ErrorReportFactory;
import com.logdog.Network.LogDogNetwork;
import com.logdog.Setting.LogDogSetting;

public class LogDogProcess {

	LogDogAlarm 		m_Alarm;
	ErrorReportFactory 	m_Factory;
	LogDogNetwork		m_Network;
	LogDogSetting		m_Setting;
	
	public LogDogProcess(LogDogAlarm alarm, ErrorReportFactory factory, 
						 LogDogNetwork network, LogDogSetting Setting) {
		// TODO Auto-generated constructor stub
		m_Alarm 	= alarm;
		m_Factory 	= factory;
		m_Network	= network;
		m_Setting	= Setting;
	}
	
	public void SendErrorReport(){
	
	}

}
