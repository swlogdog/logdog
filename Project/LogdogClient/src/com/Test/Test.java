package com.Test;

import com.google.code.microlog4android.Level;
import com.logdog.LogDog;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

public class Test extends Activity {

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.test);
	    // TODO Auto-generated method stub
	}
	
	public void Testprint(View view){
		LogDog.PrintLog(Level.INFO, "TestLog");
	}

}
