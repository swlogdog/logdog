package com.logdog.common.Network;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.logdog.common.Network.Appender.AppEngineAppender;
import com.logdog.common.Network.Appender.AppEngineSetting;
import com.logdog.common.Network.Appender.NetworkAppender;

import android.content.Context;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.util.Log;

public class LogDogNetwork {

	private List<NetworkAppender> m_NowNetworkAppenderList;
	
	public LogDogNetwork() {
		// TODO Auto-generated constructor stub
		m_NowNetworkAppenderList = new ArrayList<NetworkAppender>();
		//임시 테스트 코드
		AppEngineSetting Setting= new AppEngineSetting();
		Setting.SetURL("http://swlogdog7.appspot.com/logdog/Report/");
		AddAppender(new AppEngineAppender(Setting));
		
	}

	public void AddAppender(NetworkAppender Appender){
		m_NowNetworkAppenderList.add(Appender);
	}
	public boolean DeleteAppender(String AppenderName){
		
		Iterator<NetworkAppender> iter = m_NowNetworkAppenderList.iterator();
		
		while(iter.hasNext()){
			NetworkAppender appender = iter.next();
			if(AppenderName.equalsIgnoreCase(appender.GetClassName())){
				iter.remove();
				return true;
			}
		}
		
		return false;
	}
	public void AllDeleteAppender(){
		m_NowNetworkAppenderList.clear();
	}
	
	private NetworkAppender CreateNetworAppender(){
		return null;
	}

	
	
	public boolean SendData(Map<String,String> Data){
		for(NetworkAppender appendr : m_NowNetworkAppenderList){
			if(!appendr.SendMessage(Data))
				return false;
		}
		return true;
	}
	
	
	
	
	
	
	
	
	
	static private boolean GetNetwork(Context context,int Type){
		boolean use = false;
		try {
			PackageManager packagemanager = context.getPackageManager();
			if (packagemanager.checkPermission("android.permission.ACCESS_NETWORK_STATE",context.getPackageName()) == 0) {
				ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
				use = manager.getNetworkInfo(Type).isConnected();
			}
		} catch (Exception e) {
			Log.e("LOGDOG", e.getMessage());
		}
		return use;
	}
	
	static public boolean Get3GNetwork(Context context){
		return GetNetwork(context,ConnectivityManager.TYPE_MOBILE);
	}
	static public boolean GetWiFiNetwork(Context context){
		return GetNetwork(context,ConnectivityManager.TYPE_WIFI);
	}
}
