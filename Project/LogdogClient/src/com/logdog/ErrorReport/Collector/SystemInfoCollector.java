package com.logdog.ErrorReport.Collector;

import java.util.Locale;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.location.LocationManager;

import android.view.Display;
import android.view.WindowManager;

import com.logdog.ErrorReport.ClientReportData;

import com.logdog.common.Network.Network;

public class SystemInfoCollector {

	Context m_Context;
	
	public SystemInfoCollector(Context context) {
		// TODO Auto-generated constructor stub
		m_Context = context;
	}

	public void DoCollectSystemInfo(ClientReportData OutPutData) {
		// TODO Auto-generated method stub
 
        PackageManager packagemanager = m_Context.getPackageManager();
        try
        {
            PackageInfo packageinfo = packagemanager.getPackageInfo(m_Context.getPackageName(), 0);
            
            OutPutData.AppVersion 			= packageinfo.versionName;
            //String APP_VERSIONCODE 		= String.valueOf(packageinfo.versionCode);
            //String APP_PACKAGE 			= packageinfo.packageName;
            OutPutData.MobileNetwork 		= Network.Get3GNetwork(m_Context);
            OutPutData.WiFi			 		= Network.GetWiFiNetwork(m_Context);
            OutPutData.National 			= GetNational(m_Context);
            OutPutData.GPS					= GetGps(m_Context);
            OutPutData.OSVersion			= android.os.Build.VERSION.RELEASE;
            OutPutData.Model				= android.os.Build.MODEL;
            OutPutData.ScreenHeight			= GetHeightScreenSize(m_Context);
            OutPutData.ScreenWidth			= GetWidthScreenSize(m_Context);
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
			LocationManager locationManager = (LocationManager)context.getSystemService(Context.LOCATION_SERVICE);
			if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER))
				return false;
			else
				return true;
		}
		return false;
	}
	private int GetWidthScreenSize(Context context){
		Display display = ((WindowManager)context.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
		return display.getWidth();
	}
	private int GetHeightScreenSize(Context context){
		Display display = ((WindowManager)context.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
		return display.getHeight();
	}
}
