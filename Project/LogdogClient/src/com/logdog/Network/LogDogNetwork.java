package com.logdog.Network;

import java.util.List;

import com.logdog.Network.Appender.NetworkAppender;

public class LogDogNetwork {

	private NetworkAppender m_NowNetworkAppender;
	
	public LogDogNetwork() {
		// TODO Auto-generated constructor stub
	}

	public void SetAppender(NetworkAppender Appender){
		m_NowNetworkAppender = Appender;
	}
	
	private NetworkAppender CreateNetworAppender(){
		return null;
	}

}
