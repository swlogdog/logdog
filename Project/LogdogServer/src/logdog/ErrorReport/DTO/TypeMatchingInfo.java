package logdog.ErrorReport.DTO;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class TypeMatchingInfo {
	@XmlAttribute
	private String name;
	@XmlAttribute
	private String classname;
	@XmlAttribute
	private int line;
	@XmlAttribute
	private String ReportKey;
	@XmlAttribute
	private boolean rematching;
	
	public TypeMatchingInfo() {	}
	public TypeMatchingInfo(String name, String classname, int cline, String reportKey) {
		super();
		this.name = name;
		this.classname = classname;
		this.line=cline;
		ReportKey = reportKey;
		rematching=false;
	}
	public String getName() {
		return name;
	}
	public String getClassname() {
		return classname;
	}
	public String getReportKey() {
		return ReportKey;
	}
	public int getLine() {
		return line;
	}
	public boolean isRematching() {
		return rematching;
	}
	public void setRematching(boolean rematching) {
		this.rematching = rematching;
	}
	
	
}
