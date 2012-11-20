package logdog.ErrorReport.DTO;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 	콜스택 정보를 가지고 있는 DTO 객체 jersey를 통해 데이터를 가져온다. 
 * @since 2012. 11. 19.오전 8:01:26
 * TODO
 * @author Karuana
 */
@XmlRootElement
public class CallStackInfo {

	/**
	 * 에러 명 
	 */
	@XmlAttribute
	private String name;
	/**
	 *  클래스 명 
	 */
	@XmlAttribute
	private String classname;
	/**
	 * 라인 명 
	 */
	@XmlAttribute
	private int Line;
	
	/**
	 *  콜스택 명 
	 */
	@XmlAttribute
	private ArrayList<String> Callstack;
	
	public CallStackInfo(){}
	/**
	 * 생성자 
	 * @since 2012. 11. 19.오전 8:03:26
	 * TODO
	 * @author Karuana
	 * @param uid
	 * @param data
	 */
	public CallStackInfo(ErrorUniqueID uid, ArrayList<String> data) {
		super();
		this.name = uid.getName();
		this.Line =uid.getLine();
		this.classname=uid.getClassname();
		Callstack = data;
	}
	
	
	public int getLine() {
		return Line;
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
