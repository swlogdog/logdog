package com.logdog.Process;

import java.io.File;
import java.util.Map;

import com.google.gson.Gson;
import com.logdog.Alarm.LogDogAlarm;
import com.logdog.ErrorReport.ErrorReportFactory;
import com.logdog.ErrorReport.ReportData.ClientReportData;
import com.logdog.Network.LogDogNetwork;
import com.logdog.Setting.LogDogSetting;
import com.logdog.common.File.FileControler;

public class LogDogProcess {

	LogDogAlarm 		m_Alarm;
	ErrorReportFactory 	m_Factory;
	LogDogNetwork		m_Network;
	LogDogSetting		m_Setting;
	
	Map<String,String>	m_SendData;
	
	private static String ErrorReportFileName = "ErrorReport.txt";
	public LogDogProcess(LogDogAlarm alarm, ErrorReportFactory factory, 
						 LogDogNetwork network, LogDogSetting Setting) {
		// TODO Auto-generated constructor stub
		m_Alarm 	= alarm;
		m_Factory 	= factory;
		m_Network	= network;
		m_Setting	= Setting;
	}
	
	public boolean SendErrorReport(){
		File[] SendFileList = FileControler.ExternalStorageDirectoryFileList("LogDog");
		
		//보낼 파일이 없다면 그냥 성공
		if(SendFileList.length == 0)
			return true;
		
		boolean SendLog = false;
		//get해와야함..
		
		//post로 각각을 보내기 일단 먼저 에러리포트 전송
		Gson gson = new Gson();
				
		for (File file : SendFileList) {
			
			if(file.getName().matches("(?i).*"+ErrorReportFileName+".*")){
				String Content 		  = FileControler.FiletoString(file);
				ClientReportData Data = gson.fromJson(Content, ClientReportData.class);
				File callstackfile 	  = FileControler.GetExternalStorageFile(m_Setting.GetSaveDirPath(), 
																			 Data.CallStackFileName);
				File LogFile 		  = FileControler.GetExternalStorageFile(m_Setting.GetSaveDirPath(), 
																			 Data.LogFileName);
				
				AllDeleteSendData();
				AddSendData("JSon/ErrorReport", Content);
				AddSendData("CallStack", FileControler.FiletoString(callstackfile));
				AddSendData("CallStack", FileControler.FiletoString(LogFile));
				
				if(m_Network.SendData(GetSendData()))
					return false;
			}
		}
		return true;
	}
	
	
	public void CreateErrorReport(Throwable throwadata){
		ClientReportData data = m_Factory.CreateErrorReport(throwadata);
		SaveErrorReport(data);
	}
	
	public void SaveErrorReport(ClientReportData data){
		Gson gson = new Gson();
		String ReportJSon = gson.toJson(data);
		
		FileControler.SaveStringtoFile(ReportJSon, m_Setting.GetSaveDirPath(), data.Date + ErrorReportFileName);
	}
	
	private void AddSendData(String Key,String Data){
		m_SendData.put(Key, Data);
	}
	private void AllDeleteSendData(){
		m_SendData.clear();
	}
	private Map<String,String> GetSendData(){
		return m_SendData;
	}
}
