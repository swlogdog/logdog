package com.logdog.Test;

import java.lang.reflect.Type;

import com.google.code.microlog4android.Level;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.logdog.ErrorReport.ErrorReportFactory;
import com.logdog.ErrorReport.Collector.StackTraceCollector;
import com.logdog.ErrorReport.ReportData.ClientReportData;
import com.logdog.Setting.LogDogSetting;
import com.logdog.Test.R;
import com.logdog.log.LogDoglog4android;
import com.logdog.util.FileControler;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class MainActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        setContentView(R.layout.activity_main);
        
           //FileControler cont = new FileControler();
           
           //cont.SaveStringtoFile("srfdgsergserg", this.getPackageName(), "HAHA.txt");
        
        
        LogDogSetting setting = new LogDogSetting();
        setting.m_Context = getApplicationContext();
        setting.SetSaveDirPath("TESTlogdog");
        setting.SetReadLog(true);
        
        LogDoglog4android logger = new LogDoglog4android();
        logger.init(Level.DEBUG, setting);
        
        ErrorReportFactory factory = new ErrorReportFactory(setting);
        
        
        
        logger.error("zzzzzzzzzzzzzzzzzzzzzzz!!!@#$%SEDFGHs");
        logger.info("zzzzzzzzzzzzzzzzzzzzzzz!!!@#$%SEDFGHs");
        logger.warn("zzzzzzzzzzzzzzzzzzzzzzz!!!@#$%SEDFGHs");
        logger.fatal("zzzzzzzzzzzzzzzzzzzzzzz!!!@#$%SEDFGHs");
        logger.debug("zzzzzzzzzzzzzzzzzzzzzzz!!!@#$%SEDFGHs");
        
        
        
       try{
    	   int i = 10/0;
       }
       catch(Exception e){
    	  e.printStackTrace(); 
          ClientReportData data = factory.CreateErrorReport(e);
          Gson gson = new Gson();
          Type ClientReportDataType = new TypeToken<ClientReportData>(){}.getType();
          String JsonData = gson.toJson(data, ClientReportDataType);
          System.out.println(JsonData);
       }
       
       
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
}
