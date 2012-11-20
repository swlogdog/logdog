package logdog.ErrorReport.DTO;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 타입 매칭을 위한 기본적인 정보들을 가지고 있는 클래스 
 * @since 2012. 11. 19.오전 8:04:33
 * TODO
 * @author Karuana
 */
@XmlRootElement
public class TypeMatchingInfo {
	/**
	 * 에러 명 
	 */
	@XmlAttribute
	private String name;
	/**
	 * 클래스 명 
	 */
	@XmlAttribute
	private String classname;
	/**
	 *  코드 라인 
	 */
	@XmlAttribute
	private int line;
	/**
	 * String으로 변환한 ReportKey 
	 */
	@XmlAttribute
	private String ReportKey;
	/**
	 *  재 매칭 여부 
	 */
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
