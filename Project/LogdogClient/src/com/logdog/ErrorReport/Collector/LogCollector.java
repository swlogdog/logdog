package com.logdog.ErrorReport.Collector;

import android.util.Log;

import com.logdog.Configuration.LogDogSetting;
import com.logdog.ErrorReport.ClientReportData;
import com.logdog.common.File.FileControler;

public class LogCollector {

	LogDogSetting Setting;
	private static String SendLogFileName = "SendLogFile.txt";
	
	
	public LogCollector(LogDogSetting setting) {
		// TODO Auto-generated constructor stub
		Setting = setting;
	}

	public void DoCollectLog(ClientReportData OutputData) {
		// TODO Auto-generated method stub
		try {

			final int readline = Setting.GetReadLogLine();

			
			String totallog = FileControler.FiletoString(Setting.GetSaveDirPath(),Setting.GetLogFileName());
			if(totallog == "")
			{
				Log.e("LOGDOG","Fail Read Log File");
				return;
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
			
			OutputData.LogFileName = FileControler.SaveStringtoFile(SendLog, Setting.GetSaveDirPath() , 
																	OutputData.ReportTime+SendLogFileName );
		} 
		catch (Exception e) {
			e.printStackTrace();
			Log.e("LOGDOG", "LogReadError");
		}

	}
}
