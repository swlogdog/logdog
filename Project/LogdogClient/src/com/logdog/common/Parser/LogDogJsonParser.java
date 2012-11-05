package com.logdog.common.Parser;

import java.lang.reflect.Type;

import com.google.gson.Gson;

public class LogDogJsonParser {

	private static final Gson gson = new Gson();
	
	 public static Object fromJson(String json, Class classOfT){
		return gson.fromJson(json, classOfT);
	}
	 public Object fromJson(String json, Type typeOfT){
		 return gson.fromJson(json, typeOfT);
	 }
	 public static String toJson(Object src){
		 return gson.toJson(src);
	 }
}
