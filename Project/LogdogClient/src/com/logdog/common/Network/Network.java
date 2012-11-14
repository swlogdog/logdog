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
	
	/**
	 *  안드로이드 Context객체
	 */
	Context m_Context;
	
	/**
	 * 네트워크 객체 초기화 
	 * @since 2012. 11. 15.오전 5:49:52
	 * TODO
	 * @author JeongSeungsu
	 * @param context AndroidContext 값
	 */
	public Network(Context context){
		m_Context = context;
		NetworkCommunicatorList = new ArrayList<AbstractCommunicator>();
	}

	/**
	 * 커뮤니 케이터 추가
	 * @since 2012. 11. 15.오전 5:50:56
	 * TODO
	 * @author JeongSeungsu
	 * @param Communicator 추가할 커뮤니케이터
	 */
	public void AddCommunicator(AbstractCommunicator Communicator){
		NetworkCommunicatorList.add(Communicator);
	}
	/**
	 * 커뮤니케이터 삭제
	 * @since 2012. 11. 15.오전 5:51:10
	 * TODO
	 * @author JeongSeungsu
	 * @param CommunicatorName 삭제할 커뮤니케이터 이름
	 * @return 실패면 false 성공이면 true
	 */
	public boolean DeleteCommunicator(String CommunicatorName){
		
		Iterator<AbstractCommunicator> iter = NetworkCommunicatorList.iterator();
		
		while(iter.hasNext()){
			AbstractCommunicator appender = iter.next();
			if(CommunicatorName.equalsIgnoreCase(appender.getClass().getName())){
				iter.remove();
				return true;
			}
		}
		
		return false;
	}
	/**
	 * 모든 커뮤니케이터 삭제
	 * @since 2012. 11. 15.오전 5:51:08
	 * TODO
	 * @author JeongSeungsu
	 */
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
	
	/**
	 * 3G네트워크 상태를 얻는다.
	 * @since 2012. 11. 15.오전 5:51:43
	 * TODO
	 * @author JeongSeungsu
	 * @param context
	 * @return true면사용가능 false면 불가
	 */
	static public boolean Get3GNetwork(Context context){
		return GetNetwork(context,ConnectivityManager.TYPE_MOBILE);
	}
	/**
	 * WiFi상태를 얻는다.
	 * @since 2012. 11. 15.오전 5:52:00
	 * TODO
	 * @author JeongSeungsu
	 * @param context
	 * @return true면 가능 false면 불가
	 */
	static public boolean GetWiFiNetwork(Context context){
		return GetNetwork(context,ConnectivityManager.TYPE_WIFI);
	}
}
