package com.logdogclient;

import com.google.code.microlog4android.Level;
import com.logdog.log.LogDoglog4android;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class MainActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        LogDoglog4android logger = new LogDoglog4android();
        logger.init("Log4LogCat|Log4File", Level.DEBUG);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
}
