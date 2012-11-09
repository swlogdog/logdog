package com.logdog.common.Network;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.util.Log;

public class Network {
	
	private List<AbstractCommunicator> 	NetworkCommunicatorList;
	
	Context m_Context;
	
	public Network(Context context){
		m_Context = context;
		NetworkCommunicatorList = new ArrayList<AbstractCommunicator>();
	}
	public void AddCommunicator(AbstractCommunicator Appender){
		NetworkCommunicatorList.add(Appender);
	}
	public boolean DeleteCommunicator(String AppenderName){
		
		Iterator<AbstractCommunicator> iter = NetworkCommunicatorList.iterator();
		
		while(iter.hasNext()){
			AbstractCommunicator appender = iter.next();
			if(AppenderName.equalsIgnoreCase(appender.getClass().getName())){
				iter.remove();
				return true;
			}
		}
		
		return false;
	}
	public void AllDeleteCommunicator(){
		NetworkCommunicatorList.clear();
	}
	
	public boolean SendData(){
		for(AbstractCommunicator appendr : NetworkCommunicatorList){
			if(!appendr.SendData())
				return false;
		}
		return true;
	}
	
	public void StartService(){
		m_Context.startService(new Intent("LogDogService"));
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
