package com.logdog.Appender.AppEngine;

import java.util.ArrayList;

/**
 * 서버에서 전송받을 형태인 CallStackInfo Json에 의해 파싱되어서 사용됨
 * @since 2012. 11. 11.오후 11:41:50
 * TODO
 * @author JeongSeungsu
 */
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