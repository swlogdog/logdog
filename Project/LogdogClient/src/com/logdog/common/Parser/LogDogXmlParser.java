package com.logdog.common.Parser;

import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

public class LogDogXmlParser {

	private static final Serializer serializer = new Persister();

	public LogDogXmlParser() {
		// TODO Auto-generated constructor stub
	}

	static public void toXml(Object obj, File file){
		try {
			serializer.write(obj, file);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
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
	static public Object fromXml(Class class1, String XmlString ){
		try {
			return serializer.read(class1, XmlString);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
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
