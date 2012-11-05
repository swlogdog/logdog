package com.Test;


import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import com.logdog.LogDog;
import com.logdog.common.File.FileControler;


import android.os.Bundle;
import android.app.Activity;
import android.content.res.AssetManager;
import android.view.Menu;

public class MainActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        setContentView(R.layout.activity_main);
        
           //FileControler cont = new FileControler();
           
           //cont.SaveStringtoFile("srfdgsergserg", this.getPackageName(), "HAHA.txt");
        
        
        
		AssetManager am = getResources().getAssets();
		InputStream is;
		StringBuffer sb = null;
		try {
			is = am.open("Network.xml");
			sb = new StringBuffer();
			byte[] b = new byte[4096];
			for (int n; (n = is.read(b)) != -1;) {
				sb.append(new String(b, 0, n));
			}
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String Build = sb.toString();

	  	  
        LogDog.LogDoginitialize(getApplicationContext(),Build);

        
        
        /*
        logger.error("zzzzzzzzzzzzzzzzzzzzzzz!!!@#$%SEDFGHs");
        logger.info("zzzzzzzzzzzzzzzzzzzzzzz!!!@#$%SEDFGHs");
        logger.warn("zzzzzzzzzzzzzzzzzzzzzzz!!!@#$%SEDFGHs");
        logger.fatal("zzzzzzzzzzzzzzzzzzzzzzz!!!@#$%SEDFGHs");
        logger.debug("zzzzzzzzzzzzzzzzzzzzzzz!!!@#$%SEDFGHs");
        */
        
        
       try{
    	  String saef =null;
    	  saef.toUpperCase();
       }
       catch(Exception e){
    	  e.printStackTrace(); 
    	  LogDog.CreateLog(e);
    	  
       }
       
       
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
}
