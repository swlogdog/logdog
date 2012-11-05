package com.logdog.common.Network.Appender;

import java.util.ArrayList;

public class CallStackInfo {

	
	private String name;

	private String classname;

	private ArrayList<String> Callstack;
	
	public CallStackInfo(){}
	public CallStackInfo(String Name, String ClassName, ArrayList<String> data) {
		super();
		this.name = Name;
		this.classname = ClassName;
		Callstack = data;
	}
	
	public String getName() {
		return name;
	}
	
	public String getClassname() {
		return classname;
	}
	
	public ArrayList<String> getCallstack() {
		return Callstack;
	}

}