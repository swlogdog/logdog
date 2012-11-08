package com.logdog.Appender;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;




public class AppenderManager {

	List<IAppender> AppenderList;
	

	public AppenderManager() {
		// TODO Auto-generated constructor stub
		AppenderList = new ArrayList<IAppender>();
	}
	public List<IAppender> getAppenderList(){
		return AppenderList;
	}
	
	public void AddAppender(IAppender Appender){
		AppenderList.add(Appender);
	}
	public boolean DeleteAppender(String AppenderName){
		
		Iterator<IAppender> iter = AppenderList.iterator();
		
		while(iter.hasNext()){
			IAppender appender = iter.next();
			if(AppenderName.equalsIgnoreCase(appender.GetClassName())){
				iter.remove();
				return true;
			}
		}
		
		return false;
	}
	public void AllDeleteAppender(){
		AppenderList.clear();
	}
}