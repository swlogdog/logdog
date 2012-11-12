package com.logdog.Formatter;

import org.simpleframework.xml.Root;

import com.google.code.microlog4android.format.Formatter;

/**
 * 각 Appender에 붙을 Foramtter의 인터페이스
 * @since 2012. 11. 12.오전 12:15:29
 * TODO
 * @author JeongSeungsu
 */
@Root(name = "Formatter")
public interface IFormatter {
	
	/**
	 * Formatter 초기화
	 * @since 2012. 11. 12.오전 12:15:41
	 * TODO
	 * @author JeongSeungsu
	 */
	public void InitFormatter();
	/**
	 * Microlog4android 의 Formatter를 얻어온다.
	 * @since 2012. 11. 12.오전 12:15:47
	 * TODO
	 * @author JeongSeungsu
	 * @return  Microlog4android 의 Formatter
	 */
	public com.google.code.microlog4android.format.Formatter GetFormatter();
}
