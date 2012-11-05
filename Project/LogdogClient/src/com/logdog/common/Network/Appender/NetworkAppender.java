package com.logdog.common.Network.Appender;

import java.util.Map;


import com.logdog.common.Network.NetwrokSetting;



public interface NetworkAppender {


	/**
	 * 에러 전송
	 * @since 2012. 11. 5.오전 7:31:17
	 * TODO
	 * @author JeongSeungsu
	 * @param SendData
	 * @return
	 */
	public boolean SendMessage(Map<String,String> SendData);
	
	public boolean InitAppender(NetwrokSetting Setting);
	
	public String GetClassName();

}	
