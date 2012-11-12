package com.Test;
import com.logdog.LogDog;

import android.app.Application;


public class LogDogClient extends Application {

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		
		LogDog.LogDoginitialize(this,"LogDog.xml");
	}

}
