package com.logdog.Appender;



import com.logdog.Configuration.LogDogSetting;


/**
 * 파일에 저장할 수 있는 어펜더
 * @since 2012. 10. 13.오후 10:10:19
 * TODO 
 * @author JeongSeungsu
 */
public class FileAppender implements Appender {

	com.google.code.microlog4android.appender.FileAppender appender;
	
	public FileAppender() {
		// TODO Auto-generated constructor stub
	}

	public void CreateAppender(LogDogSetting setting) {
		// TODO Auto-generated method stub
		appender = new com.google.code.microlog4android.appender.FileAppender();
		appender.setAppend(true);
		appender.setFileName(setting.GetSaveDirPath()+"/"+setting.GetLogFileName()); //파일이름 저장시 어떤 방식으로 저장할지 포맷 설정 해야함...
	}

	public com.google.code.microlog4android.appender.Appender GetAppender() {
		// TODO Auto-generated method stub
		return (com.google.code.microlog4android.appender.Appender) appender;
	}

}
