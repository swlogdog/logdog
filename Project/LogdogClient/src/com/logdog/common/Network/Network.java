package com.logdog.common.Network;

import android.content.Context;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.util.Log;

public class Network {


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
