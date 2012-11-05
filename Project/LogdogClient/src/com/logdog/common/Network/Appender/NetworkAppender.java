package com.logdog.common.Network.Appender;

import java.util.Map;



public abstract class NetworkAppender {

	public NetworkAppender() {
		// TODO Auto-generated constructor stub
	}
	/**
	 * 에러 전송
	 * @since 2012. 11. 5.오전 7:31:17
	 * TODO
	 * @author JeongSeungsu
	 * @param SendData
	 * @return
	 */
	public abstract boolean SendMessage(Map<String,String> SendData);
	
	public abstract String GetClassName();

}	
