package com.logdog.Formatter;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

import com.google.code.microlog4android.format.Formatter;

@Root
public class SimpleFormatter implements IFormatter {
	@Element
	String Delimiter;
	com.google.code.microlog4android.format.SimpleFormatter formatter;
	
	public SimpleFormatter() {
		// TODO Auto-generated constructor stub
	}
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
