package com.logdog.Formatter;

import org.simpleframework.xml.Root;

import com.google.code.microlog4android.format.Formatter;

@Root(name = "Formatter")
public interface IFormatter {
	
	public void InitFormatter();
	public Formatter GetFormatter();
}
