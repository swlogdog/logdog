package com.logdog.Network.Appender;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import com.logdog.ErrorReport.ReportData.ClientReportData;

import android.util.Log;

public class AppEngineAppender extends NetworkAppender {

	AppEngineSetting m_Setting;
	public AppEngineAppender(AppEngineSetting setting ) {
		// TODO Auto-generated constructor stub
		m_Setting = setting;
	}
	
	@Override
	public boolean SendMessage(Map<String,String> SendData){
		boolean resualt = false;
		HttpClient client = new DefaultHttpClient();
		HttpPost post = new HttpPost(m_Setting.GetURL());
		
		try{
		      post.setHeader("Accept", "application/json");
		      post.setHeader("Content-type", "application/x-www-form-urlencoded; charset=UTF8");
		      
		      List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(1);
		      nameValuePairs.add(new BasicNameValuePair("JSon/ErrorReport",SendData.get("JSon/ErrorReport")));
		      nameValuePairs.add(new BasicNameValuePair("CallStack",SendData.get("CallStack")));
		      nameValuePairs.add(new BasicNameValuePair("Log",SendData.get("Log")));
		      
		      post.setEntity(new UrlEncodedFormEntity(nameValuePairs));
		      ///*보낼 타입 설정... 

		      		 
		      HttpResponse response = client.execute(post);
		      HttpEntity resEntity = response.getEntity();
		      
		      /*
		      BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
		      StringBuilder Request = new StringBuilder();
		      String line;
		      while ((line = rd.readLine()) != null){
		    	  	Request.append(line);
		      }
		      String ReponseString1 = Request.toString();
		      */
		      String ReponseString = EntityUtils.toString(resEntity);
		      
		      
		      int i = 10+1;
		      //
		}
		catch(IOException e){
			e.printStackTrace();
			Log.e("LOGDOG","Transmit Http Post Error");
		}
		return resualt;
	}

	@Override
	public String GetClassName() {
		// TODO Auto-generated method stub
		return this.GetClassName();
	}

}
