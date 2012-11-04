package com.logdog.NetworkAppender;

import java.util.Map;



public abstract class NetworkAppender {

	public NetworkAppender() {
		// TODO Auto-generated constructor stub
	}
	public abstract boolean SendMessage(Map<String,String> SendData);
	
	public abstract String GetClassName();

}	
