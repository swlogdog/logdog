package com.logdog.Formatter;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

import com.google.code.microlog4android.format.Formatter;

/**
 * 단순한 포맷 설정
 * @since 2012. 11. 12.오전 12:17:31
 * TODO
 * @author JeongSeungsu
 */
@Root
public class SimpleFormatter implements IFormatter {
	@Element
	String Delimiter;
	com.google.code.microlog4android.format.SimpleFormatter formatter;
	
	public SimpleFormatter() {
		// TODO Auto-generated constructor stub
	}
	/**
	 * 앞에 붙을 delimiter를 설정한다.
	 * @since 2012. 11. 12.오전 12:17:39
	 * TODO
	 * @author JeongSeungsu
	 * @param delimiter
	 */
	public SimpleFormatter(String delimiter) {
		Delimiter = delimiter;
	}

	public void InitFormatter() {
		// TODO Auto-generated method stub
		formatter = new com.google.code.microlog4android.format.SimpleFormatter();
		formatter.setDelimeter(Delimiter);
	}

	public Formatter GetFormatter() {
		// TODO Auto-generated method stub
		return formatter;
	}

}
