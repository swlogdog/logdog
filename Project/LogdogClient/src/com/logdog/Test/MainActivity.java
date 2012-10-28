package com.logdog.Test;

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
        
           FileControler cont = new FileControler();
           
           cont.SaveStringtoFile("srfdgsergserg", this.getPackageName(), "HAHA.txt");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
}
