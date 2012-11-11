package com.logdog.Appender.FileAppender;




import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

import android.util.Log;


import com.google.code.microlog4android.format.PatternFormatter;
import com.logdog.Appender.AbstractAppender;
import com.logdog.ErrorReport.ClientReportData;
import com.logdog.Formatter.IFormatter;
import com.logdog.common.File.FileControler;
import com.logdog.common.Network.Network;
import com.logdog.common.Parser.LogDogJsonParser;


/**
 * 파일에 저장할 수 있는 어펜더 Log,ErrorReport,CallStack이 저장된다.
 * @since 2012. 10. 13.오후 10:10:19
 * TODO 
 * @author JeongSeungsu
 */
@Root
public class FileAppender extends AbstractAppender{

	com.google.code.microlog4android.appender.FileAppender appender;
	
	
	private final String				StackTraceName 		= "StackTrace.txt";
	
	private final String 				ErrorReportFileName = "ErrorReport.txt";
	
	@Element
	private String						SaveDirName;
	
	@Element
	private String 						LogFileName;
	
	
	
	@Element
	IFormatter Formatter;
	
	public FileAppender(){
		super();
	}
	
	public FileAppender(String appendername, String savedirname, String logfilename, IFormatter formatter) {
		// TODO Auto-generated constructor stub
		super(appendername);
		SaveDirName = savedirname;
		LogFileName = logfilename;
		Formatter	= formatter;
	}
	
	public void InitAppender(Network network) {
		
		appender = new com.google.code.microlog4android.appender.FileAppender();
		appender.setAppend(true);
		appender.setFileName(SaveDirName+"/"+LogFileName); //파일이름 저장시 어떤 방식으로 저장할지 포맷 설정 해야함...
		Formatter.InitFormatter();
		appender.setFormatter(Formatter.GetFormatter());
	}
	
	public com.google.code.microlog4android.appender.Appender GetAppender() {
		// TODO Auto-generated method stub
		return appender;
	}


	public boolean ErrorReportProcess(ClientReportData Data) {
		// TODO Auto-generated method stub
		try {

			Data.CallStackFileName = FileControler.SaveStringtoFile(Data.CallStackFileName, 
					SaveDirName, Data.ReportTime + StackTraceName);
		
			String ReportJSon = LogDogJsonParser.toJson(Data);
			
			FileControler.SaveStringtoFile(ReportJSon, SaveDirName, Data.ReportTime + ErrorReportFileName);
		} 
		catch (Exception e) {
			e.printStackTrace();
			Log.e("LOGDOG", "LogReadError");
			return false;
		}
		return true;
	}

	public String GetClassName() {
		// TODO Auto-generated method stub
		return this.GetClassName();
	}

	public String GetStackTraceFileName(){
		return StackTraceName;
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

	

}
