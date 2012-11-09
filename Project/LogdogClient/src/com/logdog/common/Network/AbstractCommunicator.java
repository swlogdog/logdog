package com.logdog.common.Network;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Root;

@Root(name = "Communicator")
public abstract class AbstractCommunicator {
	
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

	public abstract boolean SendData();
}
