package com.logdog.log.Appender;

import com.google.code.microlog4android.appender.Appender;
import com.google.code.microlog4android.appender.FileAppender;
import com.logdog.Setting.LogDogSetting;


/**
 * 파일에 저장할 수 있는 어펜더
 * @since 2012. 10. 13.오후 10:10:19
 * TODO 
 * @author JeongSeungsu
 */
public class Log4File extends Log4Appender {

	FileAppender appender;
	
	public Log4File() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void CreateAppender(LogDogSetting setting) {
		// TODO Auto-generated method stub
		appender = new FileAppender();
		appender.setAppend(true);
		appender.setFileName(setting.GetLogFileName()); //파일이름 저장시 어떤 방식으로 저장할지 포맷 설정 해야함...
	}

	@Override
	public Appender GetAppender() {
		// TODO Auto-generated method stub
		return appender;
	}

}
