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

	public void run() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}

}
