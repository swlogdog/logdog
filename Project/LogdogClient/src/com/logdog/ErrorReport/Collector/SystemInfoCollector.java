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

/**
 * 안드로이드 시스템 정보를 모으는 콜렉터
 * @since 2012. 11. 12.오전 12:14:40
 * TODO
 * @author JeongSeungsu
 */
public class SystemInfoCollector {

	Context m_Context;
	
	public SystemInfoCollector(Context context) {
		// TODO Auto-generated constructor stub
		m_Context = context;
	}

	/**
	 * 시스템 정보를 모은다.
	 * @since 2012. 11. 12.오전 12:14:53
	 * TODO
	 * @author JeongSeungsu
	 * @param OutPutData
	 */
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
	



	/**
	 * 나라 코드를 가져온다.
	 * @since 2012. 11. 15.오전 6:00:49
	 * TODO
	 * @author JeongSeungsu
	 * @param context
	 * @return 나라코드가 제대로 안될시 Unknown반환 아니면 kr 같은 나라코드 반환
	 */
	private String GetNational(Context context){
		Locale nowlocale = context.getResources( ).getConfiguration( ).locale;
		String isNull = "";
		if(isNull.equals(nowlocale.getCountry()))
			return "unknown";
		else
			return nowlocale.getCountry(); 
	}
	/**
	 * Gps 상태를 가져온다.
	 * @since 2012. 11. 15.오전 6:01:19
	 * TODO
	 * @author JeongSeungsu
	 * @param context
	 * @return true 사용중 false 면 비사용
	 */
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
	/**
	 * 가로 스크린 사이즈를 가져온다.
	 * @since 2012. 11. 15.오전 6:01:42
	 * TODO
	 * @author JeongSeungsu
	 * @param context
	 * @return 가로 스크린 크기
	 */
	private int GetWidthScreenSize(Context context){
		Display display = ((WindowManager)context.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
		return display.getWidth();
	}
	/**
	 * 세로 스크린 사이즈를 가져온다.
	 * @since 2012. 11. 15.오전 6:01:58
	 * TODO
	 * @author JeongSeungsu
	 * @param context
	 * @return 세로 스크린 크기
	 */
	private int GetHeightScreenSize(Context context){
		Display display = ((WindowManager)context.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
		return display.getHeight();
	}
}
