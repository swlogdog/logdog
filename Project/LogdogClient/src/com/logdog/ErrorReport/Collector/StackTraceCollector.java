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
       
        ParseStackTrace(outputdata,Errorthrow);
        
        outputdata.CallStackFileName = FileControler.SaveStringtoFile(StackTraceString, 
        															  Setting.GetSaveDirPath(), 
        															  Setting.GetStackTraceFileName());
	}
	
	private void ParseStackTrace(ClientReportData outputdata,Throwable Errorthrow){
		boolean RunTimeError = false;
		
        Throwable cause = Errorthrow.getCause();
        if(cause != null)
            RunTimeError = true;
        
        Throwable recordthrow;
        if(RunTimeError)
        	recordthrow = cause;
        else
        	recordthrow = Errorthrow;
                
        outputdata.ErrorName = recordthrow.getMessage();
        StackTraceElement[] ErrorElements = recordthrow.getStackTrace();
        
        outputdata.ErrorLine	  = ErrorElements[0].getLineNumber();
        outputdata.ErrorClassName = ErrorElements[0].getClassName() + "(" + String.valueOf(outputdata.ErrorLine) + ")";
	}
}
