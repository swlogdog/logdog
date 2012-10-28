package com.logdog.ErrorReport.Collector;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import com.logdog.ErrorReport.ErrorReportData;
import com.logdog.Setting.LogDogSetting;

public class SystemInfoCollector {

	LogDogSetting Setting;
	
	public SystemInfoCollector(LogDogSetting setting) {
		// TODO Auto-generated constructor stub
		Setting = setting;
	}

	public void DoCollectSystemInfo(ErrorReportData OutPutData) {
		// TODO Auto-generated method stub
		       
                
        PackageManager packagemanager = Setting.m_Context.getPackageManager();
        try
        {
            PackageInfo packageinfo = packagemanager.getPackageInfo(Setting.m_Context.getPackageName(), 0);
            OutPutData.AppVersion = packageinfo.versionName;
            //String APP_VERSIONCODE = String.valueOf(packageinfo.versionCode);
            //String APP_PACKAGE = packageinfo.packageName;
            
            
        }
        catch(Exception e)
        {
        	e.printStackTrace();
        }
	}

}
