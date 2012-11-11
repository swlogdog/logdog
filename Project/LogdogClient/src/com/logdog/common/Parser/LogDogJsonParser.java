package com.logdog.common.Parser;

import java.lang.reflect.Type;

import com.google.gson.Gson;

/**
 * json 데이터를 시리얼라이즈, 디 시리얼라이즈 해주는 파서
 * @since 2012. 11. 11.오후 11:57:08
 * TODO
 * @author JeongSeungsu
 */
public class LogDogJsonParser {

	private static final Gson gson = new Gson();
	
	 /**
	 * Json 데이터를 Object로 바꿔준다.
	 * @since 2012. 11. 11.오후 11:57:28
	 * TODO
	 * @author JeongSeungsu
	 * @param json json데이터 스트링
	 * @param classOfT Class 형태
	 * @return class 형태의 오브젝트 인스턴스를 리턴
	 */
	public static Object fromJson(String json, Class classOfT){
		return gson.fromJson(json, classOfT);
	}
	 public Object fromJson(String json, Type typeOfT){
		 return gson.fromJson(json, typeOfT);
	 }
	 /**
	 * Object데이터를 json형태의 스트링으로 바꿔줌
	 * @since 2012. 11. 11.오후 11:58:32
	 * TODO
	 * @author JeongSeungsu
	 * @param src Object 인스턴스
	 * @return Json String 데이터
	 */
	public static String toJson(Object src){
		 return gson.toJson(src);
	 }
}
