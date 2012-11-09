package com.logdog.Formatter;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

import com.google.code.microlog4android.format.Formatter;




@Root
public class PatternFormatter implements IFormatter {

	@Element
	String PatternString;
	
	com.google.code.microlog4android.format.PatternFormatter formatter;
	
	
	public PatternFormatter(){
		PatternString = "";
	}
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
