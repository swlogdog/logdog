package com.logdog.common.Network.Appender;


import java.net.URLEncoder;
import java.util.ArrayList;

import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;

import org.apache.http.client.HttpClient;

import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;


import org.apache.http.util.EntityUtils;



import com.logdog.common.Network.NetwrokSetting;
import com.logdog.common.Parser.LogDogJsonParser;



public class AppEngineAppender implements NetworkAppender {

	AppEngineSetting m_AppEngineSetting;
	
	public boolean InitAppender(NetwrokSetting Setting) {
		
		m_AppEngineSetting = (AppEngineSetting)Setting;
		return false;
	}
	
	public AppEngineAppender() {
		// TODO Auto-generated constructor stub
	}
	
	public boolean SendMessage(Map<String,String> SendData){
		
		String errorname 	  	= SendData.get("ErrorName");
	    String errorclassname 	= SendData.get("ErrorClassName");
	    String CallStackString	= SendData.get("CallStack");
	    String ClientReportJson = SendData.get("JSon/ErrorReport");
	    String LogData			= SendData.get("Log");
	    
	    //URL에 관한 문자셋으로 변환
	    String URLerrorname = null;
	    String URLerrorclassname = null;
	    try{
	    	URLerrorname = URLEncoder.encode(errorname,"UTF-8");
	    	URLerrorclassname = URLEncoder.encode(errorclassname,"UTF-8");
	    }catch(Exception e){
	    	e.printStackTrace();
	    }
	    
	    //1 현재 에러가 서버에 존재하는지 체크
	    BooleanResult result = HttpGetExistErrorCheck(URLerrorname,URLerrorclassname);
		
	    if(result == null)
	    	return false;
	    
	    //2 성공시 콜스택과 에러정보를 보낸다.
	    if(!result.isResult()){
	    	if(!HttpPostSendNewError(errorname, errorclassname,CallStackString))
	    		return false;
	    }
	    
	    //2.1 유져 정보를 보낸다. 여기서 키값을 가져옴
	    String LogKey = HttpPostSendUserInfo(ClientReportJson);
	    
	    if(LogKey == null)
	    	return false;
	    
	    //3 서버에 로그를 전송할지 안할지 서버 셋팅에 따른 체크
	    BooleanResult Logresult = HttpGetLogSendCheck(); 
	    
	    if(Logresult == null)
	    	return false;
	    
	    //3.1 만약 서버에서 로그 받는걸 허용한다면 로그 전송 
	    if(Logresult.isResult()){
	    	if(!HttpPutSendLog(LogData, LogKey))
	    		return false; 
	    }
	    
	    return true;
	}

	public String GetClassName() {
		// TODO Auto-generated method stub
		return this.GetClassName();
	}
	
	/**
	 * 서버에 이 에러가 있나 없나 체크 
	 * @since 2012. 11. 5.오전 2:23:15
	 * TODO
	 * @author JeongSeungsu
	 * @param ErrorName
	 * @param ClassName
	 * @return null값을 리턴하면 전혀 통신이 안된것이고 True,False는 데이터가 존재하는지 안하는지 리턴..
	 */
	private BooleanResult HttpGetExistErrorCheck(String ErrorName,String ClassName) {
		return HttpGetSend(m_AppEngineSetting.GetURL()
				+ m_AppEngineSetting.GetErrorCheckUrl() + "/" + ErrorName + "/"
				+ ClassName);
	}

	/**
	 * 로그값을 받냐 안받냐 
	 * @since 2012. 11. 5.오전 3:46:06
	 * TODO
	 * @author JeongSeungsu
	 * @return null값을 리턴하면 전혀 통신이 안된것이고 True,False는 데이터가 존재하는지 안하는지 리턴..
	 */
	private BooleanResult HttpGetLogSendCheck(){
		return HttpGetSend(m_AppEngineSetting.GetURL()
				+ m_AppEngineSetting.GetLogSettingUrl());
	}
	/**
	 * 새로운 에러면 서버로 전송
	 * @since 2012. 11. 5.오전 3:10:59
	 * TODO
	 * @author JeongSeungsu
	 * @param ErrorName
	 * @param ClassName
	 * @param CallStack
	 * @return 성공하면 True 실패면 False;
	 */
	private boolean HttpPostSendNewError(String ErrorName, String ClassName,String CallStack) {
		
		ArrayList<String> callstackarray = new ArrayList<String>();
		
		String[] StrArray;
		StrArray = CallStack.split("\n");

		for (String s : StrArray) {
			callstackarray.add(s);
		}
		CallStackInfo info = new CallStackInfo(ErrorName, ClassName, callstackarray);
		
		String CallStackInfoJson = LogDogJsonParser.toJson(info); 
		String SendUrl = m_AppEngineSetting.GetURL()+m_AppEngineSetting.GetErrorCheckUrl();
		
		String Response = HttpPostSendJson(SendUrl,CallStackInfoJson);
		
		if(Response == null)
			return false;
				
	
		return true;
	}
	/**
	 * ClientErrorReport전송
	 * @since 2012. 11. 5.오전 3:34:51
	 * TODO
	 * @author JeongSeungsu
	 * @param ClientReportDataJson
	 * @return 실패시 null값 리턴 성공시 키값 가져옴
	 */
	private String HttpPostSendUserInfo(String ClientReportDataJson) {
		String SendUrl = m_AppEngineSetting.GetURL() + m_AppEngineSetting.GetSendUserInfoUrl();
		String Response = HttpPostSendJson(SendUrl,ClientReportDataJson);
		
		if(Response == null)
			return null;
			
		return Response;
	}
	
	/**
	 * 로그 전송 
	 * @since 2012. 11. 5.오전 4:01:17
	 * TODO
	 * @author JeongSeungsu
	 * @param LogData
	 * @param Key
	 * @return 
	 */
	private boolean HttpPutSendLog(String LogData,String Key){
		 
		try {
		HttpClient client 	= new DefaultHttpClient(); 
		HttpPut Put 		= new HttpPut(m_AppEngineSetting.GetURL()+
									  	  m_AppEngineSetting.GetSendUserInfoUrl()+
									  	  "/key="+Key);
		
		StringEntity input = new StringEntity(LogData);
		    input.setContentType("text/plain");
		
		Put.setEntity(input); 
		
		HttpResponse responsePut = client.execute(Put);
		
		if(!HttpSuccessResponsCode(responsePut))
			return true;
				
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return false;
	}
	
	/**
	 * 서버에 Get해서 URL에따라 셋팅값을 가져옴 
	 * @since 2012. 11. 5.오전 3:41:35
	 * TODO
	 * @author JeongSeungsu
	 * @param Url 가져올 URL
	 * @return BooleanResult => 값에 따라 설정, null = 완전 실패 
	 */
	private BooleanResult HttpGetSend(String Url){
		try {
			HttpClient client = new DefaultHttpClient();
			
			HttpGet get = new HttpGet(Url);
			HttpResponse responseGet = client.execute(get);
			HttpEntity resEntityGet = responseGet.getEntity();
			
			
			if (resEntityGet != null) {
				// 결과를 처리
				String Result = EntityUtils.toString(resEntityGet);
				BooleanResult isExist = (BooleanResult) LogDogJsonParser.fromJson(Result, BooleanResult.class);
				return isExist;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * Post방식으로 JsonData를 보낸다.
	 * @since 2012. 11. 5.오전 3:20:12
	 * TODO
	 * @author JeongSeungsu
	 * @param Url
	 * @param JsonData
	 * @return ResponseString이 온다. 완전 실패시 Null
	 */
	private String HttpPostSendJson(String Url,String JsonData){
		String Response = null;
		try {
			HttpClient client = new DefaultHttpClient();
			HttpPost post = new HttpPost(Url);
			
		    StringEntity input = new StringEntity(JsonData);
		    input.setContentType("application/json");
			
			post.setEntity(input);
			HttpResponse responsePOST = client.execute(post);
			HttpEntity resEntity = responsePOST.getEntity();

			
			if(!HttpSuccessResponsCode(responsePOST))
				return null;
			
			Response = EntityUtils.toString(resEntity);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Response;
	}
	
	private boolean HttpSuccessResponsCode(HttpResponse response){
		int Code = 202;
		int getcode = response.getStatusLine().getStatusCode();
		if(getcode == Code)
			return true;
		else
			return false;
	}


}
