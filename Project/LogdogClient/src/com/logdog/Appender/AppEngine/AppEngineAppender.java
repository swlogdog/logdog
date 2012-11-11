package com.logdog.Appender.AppEngine;


import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;


import android.util.Log;

import com.logdog.Appender.FileAppender.FileAppender;
import com.logdog.ErrorReport.ClientReportData;
import com.logdog.Formatter.IFormatter;
import com.logdog.common.File.FileControler;
import com.logdog.common.Network.Network;



/**
 * 구글 앱엔진에 데이터를 보내는 어펜더
 * FileAppender를 상속받아서 구현한다 
 * @since 2012. 11. 5.오후 10:08:22
 * TODO
 * @author JeongSeungsu
 */
@Root
public class AppEngineAppender extends FileAppender {

	private final String				SendLogFileName 	= "SendLogFile.txt";
	
	/**
	 * AppEngine과의 소통을 담당하는 커뮤니 케이터
	 */
	@Element
	AppEngineCommunicator AppComunicator;
	
	/**
	 * 보낼 로그 라인수
	 */
	@Element
	private int							ReadLogLine;
	
	/**
	 * Network객체에 등록하기 위한 더미
	 */
	Network	m_Network;

	public AppEngineAppender(){
		super();
	}
	public AppEngineAppender(String appendername, String savedirname, String logfilename, int readlogline,
							AppEngineCommunicator communicator,IFormatter formatter){
		super(appendername, savedirname, logfilename, formatter);
		ReadLogLine	   = readlogline;
		AppComunicator = communicator;
	}


	public void InitAppender(Network network) {
		// TODO Auto-generated method stub
		super.InitAppender(network);
		m_Network = network;
		AppComunicator.SetSaveDir(super.GetSaveDirName());
		AppComunicator.SetSaveErrorReportFileName(super.GetErrorReportFileName());
		m_Network.AddCommunicator(AppComunicator);
	}
	
	public boolean ErrorReportProcess(ClientReportData Data) {
		if(!super.ErrorReportProcess(Data))
			return false;
		try {

		final int readline = ReadLogLine;

		String totallog = FileControler.FiletoString(super.GetSaveDirName(),super.GetLogFileName());
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
		
		Data.LogFileName = FileControler.SaveStringtoFile(SendLog, super.GetSaveDirName() , 
																Data.ReportTime+SendLogFileName);
		
		}catch (Exception e) {
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
	public int GetRealLogLine(){
		return ReadLogLine;
	}
	
	
}
