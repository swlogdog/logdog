package com.logdog.common.Network;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.util.Log;

/**
 * 네트워크 관리하는 총괄 클래스
 * @since 2012. 11. 11.오후 11:55:13
 * TODO
 * @author JeongSeungsu
 */
public class Network {
	
	/**
	 * 커뮤니케이터 리스트를 가지고 있는다. 이것에 의해 관리된다
	 */
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
	
	/**
	 * 모든 등록된 커뮤니케이터에 데이터를 전송한다.
	 * @since 2012. 11. 11.오후 11:55:39
	 * TODO
	 * @author JeongSeungsu
	 * @return 모든 어펜더가 성공하면 성공 아니면 실패
	 */
	public boolean SendData(){
		for(AbstractCommunicator appendr : NetworkCommunicatorList){
			if(!appendr.SendData())
				return false;
		}
		return true;
	}
	
	/**
	 * 스타트를 돌려서 따로 전송하기 위한 로직 Process에서 처리 된다.
	 * @since 2012. 11. 11.오후 11:56:16
	 * TODO
	 * @author JeongSeungsu
	 */
	public void StartService(){
		m_Context.startService(new Intent("LogDogService"));
	}

	/**
	 * 네트워크 상태를 얻어오기
	 * @since 2012. 11. 11.오후 11:56:33
	 * TODO
	 * @author JeongSeungsu
	 * @param context
	 * @param Type 모바일,wifi 
	 * @return 가능하면 true, 아니면 false;
	 */
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
