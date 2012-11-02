package logdog.ErrorReport.DTO;

import java.util.ArrayList;

public class CallStackInfo {

	private String name;
	private String classname;
	private ArrayList<String> Callstack;
	
	public CallStackInfo(){}
	public CallStackInfo(ErrorUniqueID uid, ArrayList<String> data) {
		super();
		this.name = uid.getName();
		this.classname=uid.getClassname();
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
