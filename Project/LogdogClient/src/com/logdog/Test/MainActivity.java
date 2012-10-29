package com.logdog.Test;

import com.logdog.ErrorReport.ErrorReportFactory;
import com.logdog.ErrorReport.Collector.StackTraceCollector;
import com.logdog.ErrorReport.ReportData.ClientReportData;
import com.logdog.Setting.LogDogSetting;
import com.logdog.Test.R;
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
        
        ErrorReportFactory factory = new ErrorReportFactory(setting);
        
       try{
    	   int i = 10/0;
       }
       catch(Exception e){
    	  e.printStackTrace(); 
          factory.CreateErrorReport(e);
       }
               
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
}
