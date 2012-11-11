package com.logdog.common.Network;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Root;

/**
 * 네트워크 즉 다른 서버나 Appengine과 응답하기 위한 인터페이스
 * @since 2012. 11. 11.오후 11:54:21
 * TODO
 * @author JeongSeungsu
 */
@Root(name = "Communicator")
public abstract class AbstractCommunicator {
	
	/**
	 * 커뮤니케이터 이름
	 */
	@Attribute
	String CommunicatorName;
	
	public AbstractCommunicator(){
		CommunicatorName = new String();
	}
	public AbstractCommunicator(String communicatorname){
		CommunicatorName = communicatorname;
	}
	public void SetCommunicatorName(String name){
		CommunicatorName = name;
	}
	public String GetCommunicatorName(){
		return CommunicatorName;
	}

	/**
	 * 여기서 보낼 데이터처리하는 루틴을 만든다.
	 * @since 2012. 11. 11.오후 11:54:55
	 * TODO
	 * @author JeongSeungsu
	 * @return 실패,성공
	 */
	public abstract boolean SendData();
}
