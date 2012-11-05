package com.logdog.common.Network;

import java.util.List;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

@Root
public class NetworkSettingList {

	@ElementList
	List<NetwrokSetting> SettingList;
	

	public List<NetwrokSetting> getSettingList(){
		return SettingList;
	}
}