package com.logdog.ErrorReport.Collector;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;


import com.logdog.ErrorReport.ClientReportData;

/**
 * 콜스택 정보 모으는 클래스
 * @since 2012. 11. 12.오전 12:13:02
 * TODO
 * @author JeongSeungsu
 */
public class StackTraceCollector {


	public StackTraceCollector() {
		// TODO Auto-generated constructor stub
	}


	/**
	 * ClientReportData에 Exception을 해석해 Callstack 관련 Data를 넣어준다.
	 * @since 2012. 11. 12.오전 12:13:14
	 * TODO
	 * @author JeongSeungsu
	 * @param outputdata 
	 * @param Errorthrow Exception 상위 객체
	 */
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
	
	/**
	 * 콜스택 정보 파싱 해서 Data에 값의 형태로 넣어줌
	 * @since 2012. 11. 12.오전 12:14:24
	 * TODO
	 * @author JeongSeungsu
	 * @param outputdata
	 * @param Errorthrow
	 * @param StackTraceString
	 */
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
