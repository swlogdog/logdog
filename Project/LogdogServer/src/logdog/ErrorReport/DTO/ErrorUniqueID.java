package logdog.ErrorReport.DTO;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 	에러 타입을 구분지어주는 기본 정보들로 이루어진 객체 
 * @since 2012. 11. 19.오전 8:04:11
 * TODO
 * @author Karuana
 */
@XmlRootElement
public class ErrorUniqueID {
	@XmlAttribute
	private String name;
	@XmlAttribute
	private String classname;
	@XmlAttribute
	private int line;
	public ErrorUniqueID()	{}
	public ErrorUniqueID(String name, String classname,int line) {
		super();
		this.name = name;
		this.classname = classname;
		this.line=line;
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
	public int getLine() {
		return line;
	}
	public void setLine(int line) {
		this.line = line;
	}
}
