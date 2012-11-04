

import java.util.ArrayList;




public class CallStackInfo {


	private String name;

	private String classname;
	
	private ArrayList<String> Callstack;
	
	public CallStackInfo(){}
	public CallStackInfo(int data) {
		super();
		this.name = "Devided by Zero";
		this.classname="Apple.java : 10";
		Callstack = new ArrayList<String>();
		Callstack.add("Apple.java");
		Callstack.add("comTest.java");
		Callstack.add("Life.java");
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
