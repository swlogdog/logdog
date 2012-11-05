package com.logdog.Factory;

import java.io.File;

import android.util.Log;

import com.logdog.common.Network.LogDogNetwork;
import com.logdog.common.Network.NetworkSettingList;
import com.logdog.common.Network.NetwrokSetting;
import com.logdog.common.Network.Appender.NetworkAppender;
import com.logdog.common.Parser.LogDogXmlParser;


public class NetworkFactory {

	
	public static NetworkAppender CreateNetworkAppender(NetwrokSetting Setting){
		NetworkAppender appender;
		try {
			
			Class c = Class.forName(Setting.GetAppenderClassName());
			appender = (NetworkAppender) c.newInstance();
			appender.InitAppender(Setting);

		} catch (ClassNotFoundException e1) { //클래스 이름이 없다.
			Log.e("LOGDOG", "Class is Not Found");
			return null;
		} catch (InstantiationException e2) { //인스턴스를 생성 할 수 없다.
			Log.e("LOGDOG", "new Instance Fail");
			return null;
		} catch (IllegalAccessException e3) { //클래스 파일을 액세서 할 수 없다.
			Log.e("LOGDOG", "Class File Access Error");
			return null;
		}
		return appender;
	}
	public static LogDogNetwork CreateLogDogNetwork(String NetworkXml){
		
		LogDogNetwork Network = new LogDogNetwork();
		
		NetworkSettingList list = (NetworkSettingList) LogDogXmlParser.fromXml(NetworkSettingList.class,NetworkXml);
		
		for(NetwrokSetting setting : list.getSettingList()){
			NetworkAppender appender = CreateNetworkAppender(setting);
			Network.AddAppender(appender);
		}
		return Network;
	}
}
