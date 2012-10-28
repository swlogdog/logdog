package com.logdog.ErrorReport.Collector;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;



import com.logdog.ErrorReport.ErrorReportData;
import com.logdog.Setting.LogDogSetting;
import com.logdog.util.FileControler;

public class StackTraceCollector {

	LogDogSetting Setting;
	
	public StackTraceCollector(LogDogSetting setting) {
		// TODO Auto-generated constructor stub
		Setting = setting;
	}


	public void DoCollectStackTrace(ErrorReportData outputdata,Throwable Errorthrow ) {
		// TODO Auto-generated method stub
		
		
	    Writer stacktracewirter = new StringWriter();
        PrintWriter stacktraceprinter = new PrintWriter(stacktracewirter);
        
        Errorthrow.printStackTrace(stacktraceprinter);
        String StackTraceString = stacktracewirter.toString();
        stacktraceprinter.close();
       
        ParseStackTrace(outputdata,Errorthrow);
        
        outputdata.CallStackFileName = SaveFileData(outputdata.Date,StackTraceString);
	}
	
	private void ParseStackTrace(ErrorReportData outputdata,Throwable Errorthrow){
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
        
        outputdata.ErrorClassName = ErrorElements[0].getClassName();
        outputdata.ErrorLine	  = ErrorElements[0].getLineNumber();
        outputdata.ErrorFileName  = ErrorElements[0].getFileName();
	}
	
	private String SaveFileData(String Date,String StackTrace){
		String FileName;
		FileControler fcon = new FileControler();
		FileName = fcon.SaveStringtoFile(StackTrace, Setting.GetSaveDirPath(), Date + "StackTrace.txt");
		
		return FileName;
	}

}
