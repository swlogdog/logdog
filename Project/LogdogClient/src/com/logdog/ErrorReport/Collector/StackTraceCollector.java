package com.logdog.ErrorReport.Collector;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;



import com.logdog.ErrorReport.ReportData.ClientReportData;
import com.logdog.Setting.LogDogSetting;
import com.logdog.common.File.FileControler;

public class StackTraceCollector {

	LogDogSetting Setting;
	
	public StackTraceCollector(LogDogSetting setting) {
		// TODO Auto-generated constructor stub
		Setting = setting;
	}


	public void DoCollectStackTrace(ClientReportData outputdata,Throwable Errorthrow ) {
		// TODO Auto-generated method stub
		
		
	    Writer stacktracewirter = new StringWriter();
        PrintWriter stacktraceprinter = new PrintWriter(stacktracewirter);
        
        Errorthrow.printStackTrace(stacktraceprinter);
        String StackTraceString = stacktracewirter.toString();
        stacktraceprinter.close();
       
        ParseStackTrace(outputdata,Errorthrow,StackTraceString);
        
        outputdata.CallStackFileName = FileControler.SaveStringtoFile(StackTraceString, 
        															  Setting.GetSaveDirPath(), 
        															  outputdata.ReportTime + Setting.GetStackTraceFileName());
	}
	
	private void ParseStackTrace(ClientReportData outputdata,Throwable Errorthrow,String StackTraceString){
		boolean RunTimeError = false;
		
        Throwable cause = Errorthrow.getCause();
        if(cause != null)
            RunTimeError = true;
        
        Throwable recordthrow;
        if(RunTimeError)
        	recordthrow = cause;
        else
        	recordthrow = Errorthrow;
                
        String [] errorname = StackTraceString.split("\n");
        outputdata.ErrorName = errorname[0].toString(); 
        StackTraceElement[] ErrorElements = recordthrow.getStackTrace();
        
        outputdata.ErrorClassName = ErrorElements[0].getClassName() + "(" + String.valueOf(ErrorElements[0].getLineNumber()) + ")";
	}
}
