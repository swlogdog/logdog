package com.Test;

import java.io.File;

import com.logdog.common.File.FileControler;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;

/**
 * TestService
 * @since 2012. 11. 2.오전 2:56:26
 * TODO
 * @author JeongSeungsu
 */
public class TestService extends Service implements Runnable{
	private Handler m_Handler;
	private boolean m_Running;
	
	/**
	 * 15분마다 체크
	 */
	private static final int CHECK_TIME = 15 * (60*1000);
	
	public TestService() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		m_Running = false;
		m_Handler = new Handler();
	}

	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		// TODO Auto-generated method stub
				
		m_Running = true;
		//m_Handler.postDelayed(this, CHECK_TIME);
				
		return super.onStartCommand(intent, flags, startId);
	}

	public void run() {
		// TODO Auto-generated method stub
		File[] SendFileList = FileControler.ExternalStorageDirectoryFileList("LogDog");
		
		if(SendFileList.length == 0)
			this.stopSelf();
		
		
		m_Handler.postDelayed(this, CHECK_TIME);
		
	}

}
