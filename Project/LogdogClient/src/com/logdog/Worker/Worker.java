package com.logdog.Worker;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import android.content.Context;
import android.content.Intent;

import com.logdog.Appender.AppenderManager;
import com.logdog.Appender.IAppender;
import com.logdog.Configuration.LogDogConfiguration;
import com.logdog.ErrorReport.ClientReportData;

import com.logdog.Worker.Factory.ErrorReportFactory;
import com.logdog.common.File.FileControler;
import com.logdog.common.Network.Network;
import com.logdog.common.Parser.LogDogJsonParser;


public class Worker {
	
	private static Worker instance;

	public static Worker getInstance() {
		if (instance == null) {
			synchronized (Worker.class) { 
				if (instance == null) 
					instance = new Worker(); 
			}
		}
		return instance;
	}

	private LogDogConfiguration 	Setting;
	private ErrorReportFactory		Factory;
	
	AppenderManager					appendermanager;
	
	
	
	Map<String,String>	m_SendData;
	
	
	
	public Worker() {
		// TODO Auto-generated constructor stub
		Setting 		 = new LogDogConfiguration();
		
		
		Factory 		 = new ErrorReportFactory(Setting);
		m_SendData  	 = new HashMap<String, String>();
		
	}
	
	public void InitLogDogProcess(Context context,String NetworkXml){
		Setting.m_Context = context;
	}
	
	public boolean SendErrorReport(){
		File[] SendFileList = FileControler.ExternalStorageDirectoryFileList(Setting.GetSaveDirPath());
		
		//보낼 파일이 없다면 그냥 성공
		if(SendFileList.length == 0)
			return true;
		
		//get해와야함..
		
		//post로 각각을 보내기 일단 먼저 에러리포트 전송
		for (File file : SendFileList) {
			
			if(file.getName().matches("(?i).*"+ErrorReportFileName+".*")){
				String Content 		  = FileControler.FiletoString(file);
				ClientReportData Data = (ClientReportData) LogDogJsonParser.fromJson(Content, ClientReportData.class);
				File callstackfile 	  = FileControler.GetExternalStorageFile(Setting.GetSaveDirPath(), 
																			 Data.CallStackFileName);
				File LogFile 		  = FileControler.GetExternalStorageFile(Setting.GetSaveDirPath(), 
																			 Data.LogFileName);
				
				AllDeleteSendData();
				AddSendData("JSon/ErrorReport", Content);
				AddSendData("CallStack", FileControler.FiletoString(callstackfile));
				AddSendData("Log", FileControler.FiletoString(LogFile));
				AddSendData("ErrorName", Data.ErrorName);
				AddSendData("ErrorClassName", Data.ErrorClassName);
				
				
				for(IAppender appender : appendermanager.getAppenderList()){
					if(!appender.NetworkProcess(GetSendData()))
						return false;
				}
				
				
				//ErrorReportData, CallStackFile, LogFile 삭제..
				file.delete();
				FileControler.DeleteFile(Setting.GetSaveDirPath(), Data.CallStackFileName);
				FileControler.DeleteFile(Setting.GetSaveDirPath(), Data.LogFileName);
				
			}
		}
		return true;
	}
	
	
	public void CreateErrorReport(Throwable throwadata){
		ClientReportData data = Factory.CreateErrorReport(appendermanager,throwadata);
		SaveErrorReport(data);
		Setting.m_Context.startService(new Intent("LogDogService"));
		
	}
	
	public void SaveErrorReport(ClientReportData data){
		String ReportJSon = LogDogJsonParser.toJson(data);
		
		FileControler.SaveStringtoFile(ReportJSon, Setting.GetSaveDirPath(), data.ReportTime + ErrorReportFileName);
	}
	
	public LogDogConfiguration GetSetting(){
		return Setting;
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
