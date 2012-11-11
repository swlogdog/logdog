package com.logdog.common.Parser;

import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

/**
 * XML 데이터를 시리얼라이즈, 디 시리얼라이즈 해주는 파서
 * @since 2012. 11. 11.오후 11:57:24
 * TODO
 * @author JeongSeungsu
 */
public class LogDogXmlParser {

	private static final Serializer serializer = new Persister();

	public LogDogXmlParser() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * Object 인스턴스를 XML형태의 file로 저장
	 * @since 2012. 11. 11.오후 11:59:24
	 * TODO
	 * @author JeongSeungsu
	 * @param obj 저장될 Object
	 * @param file 저장될 파일
	 */
	static public void toXml(Object obj, File file){
		try {
			serializer.write(obj, file);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * Xml 파일을 읽어서 Class 형태의 Object인스턴스를 반환
	 * @since 2012. 11. 12.오전 12:00:00
	 * TODO
	 * @author JeongSeungsu
	 * @param class1 클래스 타입
	 * @param file XMl데이터가 저장된 파일
	 * @return 클래스타입의 Object 인스턴스
	 */
	static public Object fromXml(Class class1, File file ){
		File source = new File("example.xml");

		try {
			return serializer.read(class1, source);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * XML String 데이터를 Object 인스턴스로 생성
	 * @since 2012. 11. 12.오전 12:01:44
	 * TODO
	 * @author JeongSeungsu
	 * @param class1 클래스 타입
	 * @param XmlString XML 데이터가 들어 있는 문자열
	 * @return Object 인스턴스
	 */
	static public Object fromXml(Class class1, String XmlString ){
		try {
			return serializer.read(class1, XmlString);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 정규 표현식으로 XML 데이터에 들어가 있는 테그에 따라 String을 변환
	 * <Log> Data </log> <abc> kkkkk </abc>
	 * Separate 값이 log 면 <Log> Data </log> 만 추출 
	 * @since 2012. 11. 12.오전 12:02:22
	 * TODO
	 * @author JeongSeungsu
	 * @param XMLData XML Data String
	 * @param Separate 나눌 Tag 이름
	 * @return Tag안의 내용이 포함된 String
	 */
	//<style[^>]*>.*</style>
	static public String Separate(String XMLData, String Separate){
		String RegularExpression = "(<"+Separate+">" + ".*" + "</" + Separate +">)";
		Pattern XmlPattern = Pattern.compile(RegularExpression,Pattern.DOTALL);
		Matcher matcher = XmlPattern.matcher(XMLData);
		if(matcher.find())
			return matcher.group(1);
		return null;
	}
	
	
}
