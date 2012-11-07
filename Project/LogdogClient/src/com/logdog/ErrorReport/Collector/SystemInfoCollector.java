package com.logdog.ErrorReport.Collector;

import java.util.Locale;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import android.view.Display;
import android.view.WindowManager;

import com.logdog.Configuration.LogDogSetting;
import com.logdog.ErrorReport.ClientReportData;

import com.logdog.common.Network.Network;

public class SystemInfoCollector {

	LogDogSetting Setting;
	
	public SystemInfoCollector(LogDogSetting setting) {
		// TODO Auto-generated constructor stub
		Setting = setting;
	}

	public void DoCollectSystemInfo(ClientReportData OutPutData) {
		// TODO Auto-generated method stub
 
        PackageManager packagemanager = Setting.m_Context.getPackageManager();
        try
        {
            PackageInfo packageinfo = packagemanager.getPackageInfo(Setting.m_Context.getPackageName(), 0);
            
            OutPutData.AppVersion 			= packageinfo.versionName;
            //String APP_VERSIONCODE 		= String.valueOf(packageinfo.versionCode);
            //String APP_PACKAGE 			= packageinfo.packageName;
            OutPutData.MobileNetwork 		= Network.Get3GNetwork(Setting.m_Context);
            OutPutData.WiFi			 		= Network.GetWiFiNetwork(Setting.m_Context);
            OutPutData.National 			= GetNational(Setting.m_Context);
            OutPutData.GPS					= GetGps(Setting.m_Context);
            OutPutData.OSVersion			= android.os.Build.VERSION.RELEASE;
            OutPutData.Model				= android.os.Build.MODEL;
            OutPutData.ScreenHeight			= GetHeightScreenSize(Setting.m_Context);
            OutPutData.ScreenWidth			= GetWidthScreenSize(Setting.m_Context);
        }
        catch(Exception e)
        {
        	e.printStackTrace();
        }
	}
	



	private String GetNational(Context context){
		Locale nowlocale = context.getResources( ).getConfiguration( ).locale;
		return nowlocale.getDisplayName();
	}
	private boolean GetGps(Context context) {
		PackageManager packagemanager = context.getPackageManager();
		if (packagemanager.checkPermission("android.permission.ACCESS_FINE_LOCATION",context.getPackageName()) == 0) {
			LocationManager locationManager = (LocationManager)context.getSystemService(context.LOCATION_SERVICE);
			if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER))
				return false;
			else
				return true;
		}
		return false;
	}
	private int GetWidthScreenSize(Context context){
		Display display = ((WindowManager)context.getSystemService(context.WINDOW_SERVICE)).getDefaultDisplay();
		return display.getWidth();
	}
	private int GetHeightScreenSize(Context context){
		Display display = ((WindowManager)context.getSystemService(context.WINDOW_SERVICE)).getDefaultDisplay();
		return display.getHeight();
	}
}
