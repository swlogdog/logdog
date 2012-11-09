package com.logdog.Appender.AppEngine;

/**
 * 서버에서 전송 받는 BooleanResult Json에 의해 파싱되어서 사용됨
 * @since 2012. 11. 5.오후 10:09:42
 * TODO
 * @author JeongSeungsu
 */
public class BooleanResult {
	
	private boolean result;
	public BooleanResult(){}
	public BooleanResult(boolean result) {
		super();
		this.result = result;
	}

	public boolean isResult() {
		return result;
	}

	public void setResult(boolean result) {
		this.result = result;
	}
	
}