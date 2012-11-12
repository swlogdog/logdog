package com.logdog.Formatter;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

import com.google.code.microlog4android.format.Formatter;




/**
 * 각 패턴의 입력에 따른 포맷지정
 * @since 2012. 11. 12.오전 12:16:50
 * TODO
 * @author JeongSeungsu
 */
@Root
public class PatternFormatter implements IFormatter {

	@Element
	String PatternString;
	
	com.google.code.microlog4android.format.PatternFormatter formatter;
	
	
	public PatternFormatter(){
		PatternString = "";
	}
	/**
	 * 패턴 설정
	 * @since 2012. 11. 12.오전 12:17:11
	 * TODO
	 * @author JeongSeungsu
	 * @param pattern 이 값에 String 데이터로 포맷을 설정한다.
	 */
	public PatternFormatter(String pattern) {
		// TODO Auto-generated constructor stub
		PatternString = pattern;
	}

	public Formatter GetFormatter() {
		// TODO Auto-generated method stub
		return formatter;
	}

	public void InitFormatter() {
		// TODO Auto-generated method stub
		formatter = new com.google.code.microlog4android.format.PatternFormatter();
		formatter.setPattern(PatternString);
		
	}

}
