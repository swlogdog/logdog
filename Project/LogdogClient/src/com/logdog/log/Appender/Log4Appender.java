package com.logdog.log.Appender;

import com.google.code.microlog4android.appender.Appender;
import com.logdog.Setting.LogDogSetting;



/**
 *  각 어펜더의 추상 인터페이스
 *  어펜더 생성에 관한 부분에 대해 일괄적으로 처리 하도록 랩퍼 해준 것임.
 * @since 2012. 10. 13.오후 10:09:34
 * TODO 
 * @author JeongSeungsu
 */
public abstract class Log4Appender 
{

	public abstract void CreateAppender(LogDogSetting setting);
	
	public abstract Appender GetAppender();
	
}
