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
	private String ReportKey;
	public TypeMatchingInfo() {	}
	public TypeMatchingInfo(String name, String classname, String reportKey) {
		super();
		this.name = name;
		this.classname = classname;
		ReportKey = reportKey;
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
	
	
}
