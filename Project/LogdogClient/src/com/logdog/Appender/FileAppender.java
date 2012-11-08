package com.logdog.Appender;



import java.util.Map;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

import android.util.Log;

import com.logdog.Configuration.LogDogConfiguration;
import com.logdog.ErrorReport.ClientReportData;
import com.logdog.common.File.FileControler;
import com.logdog.common.Parser.LogDogJsonParser;


/**
 * 파일에 저장할 수 있는 어펜더
 * @since 2012. 10. 13.오후 10:10:19
 * TODO 
 * @author JeongSeungsu
 */
@Root
public class FileAppender implements IAppender {

	com.google.code.microlog4android.appender.FileAppender appender;
	
	
	private final String				StackTraceName 		= "StackTrace.txt";
	private final String				SendLogFileName 	= "SendLogFile.txt";
	private final String 				ErrorReportFileName = "ErrorReport.txt";
	
	@Element
	private String						SaveDirName;
	
	@Element
	private String 						LogFileName;
	
	@Element
	private int							ReadLogLine;
	
	
	public FileAppender() {
		// TODO Auto-generated constructor stub
	}
	public void InitAppender() {
		
		appender = new com.google.code.microlog4android.appender.FileAppender();
		appender.setAppend(true);
		appender.setFileName(SaveDirName+"/"+LogFileName); //파일이름 저장시 어떤 방식으로 저장할지 포맷 설정 해야함...
	}
	
	public com.google.code.microlog4android.appender.Appender GetAppender() {
		// TODO Auto-generated method stub
		return (com.google.code.microlog4android.appender.Appender) appender;
	}

	public boolean NetworkProcess(Map<String, String> SendData) {
		// TODO Auto-generated method stub
		return true;
	}

	public boolean ErrorReportProcess(ClientReportData Data) {
		// TODO Auto-generated method stub
		
		Data.CallStackFileName = FileControler.SaveStringtoFile(Data.CallStackFileName, 
				SaveDirName, Data.ReportTime + StackTraceName);
		try {

			final int readline = ReadLogLine;

			String totallog = FileControler.FiletoString(SaveDirName,LogFileName);
			if(totallog == "")
			{
				Log.e("LOGDOG","Fail Read Log File");
				return false;
			}
			
			String[] StrArray;
			StrArray = totallog.split("\\n");

			int start = StrArray.length - readline;
			if(start < 0)
				start = 0;
			
			StringBuilder SendlogBuild = new StringBuilder(); 
			for(int i = start; i < StrArray.length; i++){
				SendlogBuild.append(StrArray[i]).append("\n");
			}
			String SendLog = SendlogBuild.toString();
			
			Data.LogFileName = FileControler.SaveStringtoFile(SendLog, SaveDirName , 
																	Data.ReportTime+SendLogFileName );
			
			
			String ReportJSon = LogDogJsonParser.toJson(Data);
			
			FileControler.SaveStringtoFile(ReportJSon, SaveDirName, Data.ReportTime + ErrorReportFileName);
		} 
		catch (Exception e) {
			e.printStackTrace();
			Log.e("LOGDOG", "LogReadError");
		}
		return true;
	}

	public String GetClassName() {
		// TODO Auto-generated method stub
		return FileAppender.class.getName();
	}

	public String GetStackTraceFileName(){
		return StackTraceName;
	}
	public String GetSendLogFileName(){
		return SendLogFileName;
	}
	public String GetErrorReportFileName(){
		return ErrorReportFileName;
	}
	public String GetSaveDirName(){
		return SaveDirName;
	}
	public String GetLogFileName(){
		return LogFileName;
	}
	public int GetRealLogLine(){
		return ReadLogLine;
	}
	
	

}
