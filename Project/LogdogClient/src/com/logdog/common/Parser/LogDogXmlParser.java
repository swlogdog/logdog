package com.logdog.common.Parser;

import java.io.File;

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
	
	
}
