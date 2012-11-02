package logdog.ErrorReport.DTO;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ErrorUniqueID {
	@XmlAttribute
	private String name;
	@XmlAttribute
	private String classname;

	public ErrorUniqueID(String name, String classname) {
		super();
		this.name = name;
		this.classname = classname;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getClassname() {
		return classname;
	}
	public void setClassname(String classname) {
		this.classname = classname;
	}
}
