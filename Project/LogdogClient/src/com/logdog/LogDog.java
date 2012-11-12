package com.logdog;
/*
 * 더블 체크드 락킹....
 * 팩토리로 만들어서 사용자가 팩토리를 사용하도록 
 * 4개의 레벨, 어펜더, 포매터, 리포트 
 * 서비스 형태로 제작....
 * 하나의 인터페이스...
 * 개발 기간에도 사용 배포에서도 사용
 */

import java.io.IOException;
import java.io.InputStream;

import com.google.code.microlog4android.Level;

import com.logdog.Handler.ExceptionHandler;
import com.logdog.Worker.Worker;


import android.content.Context;
import android.content.res.AssetManager;
import android.util.Log;

/**
 * 사용자가 최종으로 사용하는 인터페이스 적인 객체
 * @since 2012. 11. 12.오전 12:28:10
 * TODO
 * @author JeongSeungsu
 */
public final class LogDog {
	
	private static ExceptionHandler	ExceptionHandler;
	

	/**
	 * 로그도그 초기화
	 * @since 2012. 11. 12.오전 12:28:31
	 * TODO
	 * @author JeongSeungsu
	 * @param context 
	 * @param XMLFile XML 설정 데이터
	 */
	public static void LogDoginitialize(Context context,String XMLFile) { 
		String XMLString = GetXMLData(context,XMLFile);
		if(XMLString == null){
			Log.e("LOGDOG","FAIL INIT");
			return;
		}
		Worker.getInstance().InitLogDogProcess(context,XMLString);
		ExceptionHandler = new ExceptionHandler(Worker.getInstance());
	}
	
	/**
	 * Log level 설정
	 * @since 2012. 11. 12.오전 12:28:46
	 * TODO
	 * @author JeongSeungsu
	 * @param level
	 */
	public static void SetLogLever(Level level){
		Worker.getInstance().SetLogLever(level);
	}
	
	/**
	 * 로그 출력
	 * @since 2012. 11. 12.오전 12:28:55
	 * TODO
	 * @author JeongSeungsu
	 * @param level
	 * @param log
	 */
	public static void PrintLog(Level level,String log){
		Worker.getInstance().PrintLog(level, log);
	}
	
	/**
	 * Exception 출력
	 * @since 2012. 11. 12.오전 12:29:05
	 * TODO
	 * @author JeongSeungsu
	 * @param level
	 * @param t
	 */
	public static void PrintLog(Level level,Throwable t){
		Worker.getInstance().PrintLog(level, t);
	}
	
	/**
	 * Asset폴더에 존재하는 xml 파일 데이터를 가져온다.
	 * @since 2012. 11. 12.오전 12:29:12
	 * TODO
	 * @author JeongSeungsu
	 * @param context
	 * @param XMLFile
	 * @return xml 파일에 대한 String으로 변환
	 */
	private static String GetXMLData(Context context,String XMLFile){
		AssetManager am = context.getResources().getAssets();
		InputStream is;
		StringBuffer sb = null;
		try {
			is = am.open(XMLFile);
			sb = new StringBuffer();
			byte[] b = new byte[4096];
			for (int n; (n = is.read(b)) != -1;) {
				sb.append(new String(b, 0, n));
			}
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String Build = sb.toString();
		return Build;
	}
}
