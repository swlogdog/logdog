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
        
           //FileControler cont = new FileControler();
           
           //cont.SaveStringtoFile("srfdgsergserg", this.getPackageName(), "HAHA.txt");
        
        
   
 
   /*    
        LogConfiguration conf = new LogConfiguration();
        
        conf.SetLevel("DEbug");
        
        FileControler.SaveStringtoFile("", "", "test1.xml");
        File file = FileControler.GetExternalStorageFile("", "test1.xml");
        
        
        AppenderConfiguration confi = new AppenderConfiguration(); 
        
        PatternFormatter formatter = new PatternFormatter("   %d{ISO8601}    [%P]  %m  %T  ");
        formatter.InitFormatter();
        
        SimpleFormatter simple = new SimpleFormatter("delimiter");
        
        FileAppender fileappender = new FileAppender("File", "savedirname", "Savelogfilename", 20, formatter);
        
        LogCatAppender logcat = new LogCatAppender("LogCat",simple);
        
        confi.AddAppender(fileappender);
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
        
        //Set Log Level in code 
        LogDog.SetLogLever(Level.DEBUG);
        LogDog.SetLogLever(Level.INFO);
        LogDog.SetLogLever(Level.ERROR);
        LogDog.SetLogLever(Level.FATAL);
        LogDog.SetLogLever(Level.WARN);
        
        //print Log Data
        LogDog.PrintLog(Level.DEBUG, "TestDebug");
        LogDog.PrintLog(Level.INFO, "TestINFO");
        LogDog.PrintLog(Level.ERROR, "TestERROR");
        LogDog.PrintLog(Level.FATAL, "TestFATAL");
        LogDog.PrintLog(Level.WARN, "TestWARN");
                
        try{
        	  String nulldata =null;
        	  nulldata.toUpperCase();
        	  }
           catch(Exception e){
        	   //nullpoint Exception
        	   e.printStackTrace();
        	   //print Exception
        	   LogDog.PrintLog(Level.DEBUG, e);
           }
 
      

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
    
    public void TestActivity(View view){
    	Intent intent = new Intent(this,Test.class);
    	startActivity(intent);
    	finish();
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
}
