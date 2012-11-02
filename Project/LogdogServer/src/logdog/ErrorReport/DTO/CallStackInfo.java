package logdog.ErrorReport.DTO;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class CallStackInfo {

	@XmlAttribute
	private String name;
	@XmlAttribute
	private String classname;
	@XmlAttribute
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
