package com.logdog.ErrorReport.Collector;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;



import com.logdog.Configuration.LogDogConfiguration;
import com.logdog.ErrorReport.ClientReportData;
import com.logdog.common.File.FileControler;

public class StackTraceCollector {


	public StackTraceCollector() {
		// TODO Auto-generated constructor stub
	}


	public void DoCollectStackTrace(ClientReportData outputdata,Throwable Errorthrow ) {
		// TODO Auto-generated method stub
		
		
	    Writer stacktracewirter = new StringWriter();
        PrintWriter stacktraceprinter = new PrintWriter(stacktracewirter);
        
        Errorthrow.printStackTrace(stacktraceprinter);
        String StackTraceString = stacktracewirter.toString();
        stacktraceprinter.close();
       
        ParseStackTrace(outputdata,Errorthrow,StackTraceString);
        
        outputdata.CallStackFileName = StackTraceString;
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
