package com.Test;




import java.io.File;

import com.google.code.microlog4android.Level;
import com.logdog.LogDog;
import com.logdog.Appender.AppenderConfiguration;
import com.logdog.Appender.AppEngine.AppEngineAppender;
import com.logdog.Appender.AppEngine.AppEngineCommunicator;
import com.logdog.Appender.FileAppender.FileAppender;
import com.logdog.Appender.LogCatAppender.LogCatAppender;
import com.logdog.Formatter.PatternFormatter;
import com.logdog.Formatter.SimpleFormatter;
import com.logdog.Log.LogConfiguration;
import com.logdog.common.File.FileControler;
import com.logdog.common.Parser.LogDogXmlParser;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {

	Button button1;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        setContentView(R.layout.activity_main);
        
      
        //Set Log Level in code 
        LogDog.SetLogLevel(Level.DEBUG);
        LogDog.SetLogLevel(Level.INFO);
        LogDog.SetLogLevel(Level.ERROR);
        LogDog.SetLogLevel(Level.FATAL);
        LogDog.SetLogLevel(Level.WARN);
        
        
        //print Log Data
        LogDog.Print(Level.DEBUG, "TestDebug");
        LogDog.Print(Level.INFO, "TestINFO");
        LogDog.Print(Level.ERROR, "TestERROR");
        LogDog.Print(Level.FATAL, "TestFATAL");
        LogDog.Print(Level.WARN, "TestWARN");
               
        try{
        	  String nulldata =null;
        	  nulldata.toUpperCase();
        	  }
           catch(Exception e){
        	   //nullpoint Exception
        	   e.printStackTrace();
        	   //print Exception
        	   LogDog.Print(Level.DEBUG, e);
           }
           
 
      

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
}
