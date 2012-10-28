package com.logdog.server.Model.Controller;

import com.google.appengine.api.datastore.Key;
import com.logdog.server.Model.ErrorClassifiedInfo;

public class ErrorClassifier {

	
	
	public boolean IsErrorType(String name, String classname)
	{
		return true;
	}
	
	public boolean InsertErrorType(String name, String classname)
	{
		
		
		return true;
	}
	
	public boolean UpdateErrorType(String name, String classname)
	{
		
		return true;
	}
	
	public ErrorClassifiedInfo getErrorTypeInfo(String name, String classname)
	{
		return null;
		
	}
	
	public ErrorClassifiedInfo getErrorTypeInfo(Key ErrorKey)
	{
		
		return null;
	}
	
	public Key getErrorTypeKey(String name, String classname)
	{
		return null;
	}
	
}
