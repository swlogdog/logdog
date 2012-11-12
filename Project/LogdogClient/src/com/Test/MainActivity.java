package com.Test;



import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

import com.google.code.microlog4android.Level;
import com.logdog.LogDog;
import com.logdog.Appender.AppenderConfiguration;
import com.logdog.Appender.AppEngine.AppEngineAppender;
import com.logdog.Appender.AppEngine.AppEngineCommunicator;
import com.logdog.Appender.LogCatAppender.LogCatAppender;
import com.logdog.Formatter.PatternFormatter;
import com.logdog.Worker.Log.LogConfiguration;
import com.logdog.common.File.FileControler;
import com.logdog.common.Parser.LogDogXmlParser;


import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {

	Button button1;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        setContentView(R.layout.activity_main);
        
           //FileControler cont = new FileControler();
           
           //cont.SaveStringtoFile("srfdgsergserg", this.getPackageName(), "HAHA.txt");
        LogDog.LogDoginitialize(getApplicationContext(),"LogDog.xml");

 
       /*
        LogConfiguration conf = new LogConfiguration();
        
        conf.SetLevel("DEbug");
        
        FileControler.SaveStringtoFile("", "", "test1.xml");
        File file = FileControler.GetExternalStorageFile("", "test1.xml");
        
        
        AppenderConfiguration confi = new AppenderConfiguration(); 
        
        PatternFormatter formatter = new PatternFormatter("   %d{ISO8601}    [%P]  %m  %T  ");
        formatter.InitFormatter();
        
        AppEngineCommunicator comm = new AppEngineCommunicator("AppEngineComm","URL!~!!~!@");
        AppEngineAppender AppEngine = new AppEngineAppender("AppEngine","savedir","logfilename",100,comm,formatter);
        
        LogCatAppender logcat = new LogCatAppender("LogCat",formatter);
        
        confi.AddAppender(AppEngine);
        confi.AddAppender(logcat);
        
        
        
        
        LogDogXmlParser.toXml(confi, file);
        
        String Test = FileControler.FiletoString(file);
        
        int i = 10;
        */
        
        /*
        logger.error("zzzzzzzzzzzzzzzzzzzzzzz!!!@#$%SEDFGHs");
        logger.info("zzzzzzzzzzzzzzzzzzzzzzz!!!@#$%SEDFGHs");
        logger.warn("zzzzzzzzzzzzzzzzzzzzzzz!!!@#$%SEDFGHs");
        logger.fatal("zzzzzzzzzzzzzzzzzzzzzzz!!!@#$%SEDFGHs");
        logger.debug("zzzzzzzzzzzzzzzzzzzzzzz!!!@#$%SEDFGHs");
        */
        
        LogDog.PrintLog(Level.DEBUG, "TestDebug");
        LogDog.PrintLog(Level.INFO, "TestDebug");
        LogDog.PrintLog(Level.ERROR, "TestDebug");
        LogDog.PrintLog(Level.FATAL, "TestDebug");
        LogDog.PrintLog(Level.WARN, "TestDebug");
        
        
 
      
  
    }

    public void Test(View view){
    	String saef =null;
  	  saef.toUpperCase();
  	  
    }
    public void Test1(View view){
        try{
      	  String saef =null;
      	  saef.toUpperCase();}
         catch(Exception e){
      	   e.printStackTrace();
      	   LogDog.PrintLog(Level.DEBUG, e);
         }
    }
    
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
}
