package com.logdog.Process;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import android.content.Context;
import android.content.Intent;

import com.google.code.microlog4android.Level;

import com.logdog.ErrorReport.ErrorReportFactory;
import com.logdog.ErrorReport.ReportData.ClientReportData;
import com.logdog.Factory.NetworkFactory;

import com.logdog.Setting.LogDogSetting;
import com.logdog.common.File.FileControler;
import com.logdog.common.Network.LogDogNetwork;
import com.logdog.common.Parser.LogDogJsonParser;


public class LogDogProcess {
	
	private static LogDogProcess instance;

	public static LogDogProcess getInstance() {
		if (instance == null) {
			synchronized (LogDogProcess.class) { 
				if (instance == null) 
					instance = new LogDogProcess(); 
			}
		}
		return instance;
	}

	private LogDogSetting 			Setting;
	private LogDogNetwork 			Network;
	private ErrorReportFactory		Factory;
	
	
	
	Map<String,String>	m_SendData;
	
	private static final String ErrorReportFileName = "ErrorReport.txt";
	
	public LogDogProcess() {
		// TODO Auto-generated constructor stub
		Setting 		 = new LogDogSetting();
		
		
		Factory 		 = new ErrorReportFactory(Setting);
		m_SendData  	 = new HashMap<String, String>();
		
	}
	
	public void InitLogDogProcess(Context context,String NetworkXml){
		
		Network 		 = NetworkFactory.CreateLogDogNetwork(NetworkXml);
		
		Setting.m_Context = context;
				
		////////////////////////////////////////////
        Setting.SetSaveDirPath("TESTlogdog");
        Setting.SetReadLog(true);
 
        ////////////////////////////////////////////
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
				
				
				if(!Network.SendData(GetSendData()))
					return false;
				
				//ErrorReportData, CallStackFile, LogFile 삭제..
				file.delete();
				FileControler.DeleteFile(Setting.GetSaveDirPath(), Data.CallStackFileName);
				FileControler.DeleteFile(Setting.GetSaveDirPath(), Data.LogFileName);
				
			}
		}
		return true;
	}
	
	
	public void CreateErrorReport(Throwable throwadata){
		ClientReportData data = Factory.CreateErrorReport(throwadata);
		SaveErrorReport(data);
		Setting.m_Context.startService(new Intent("LogDogService"));
		
	}
	
	public void SaveErrorReport(ClientReportData data){
		String ReportJSon = LogDogJsonParser.toJson(data);
		
		FileControler.SaveStringtoFile(ReportJSon, Setting.GetSaveDirPath(), data.ReportTime + ErrorReportFileName);
	}
	
	public LogDogSetting GetSetting(){
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
