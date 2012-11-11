package com.logdog.Appender;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;




/**
 * 어펜더 설정 
 * @since 2012. 11. 11.오후 11:53:26
 * TODO
 * @author JeongSeungsu
 */
@Root(name = "Appenders")
public class AppenderConfiguration {

	/**
	 * 여기에 어펜더 리스트가 들어간다. 넣고 빼고 자유롭게
	 */
	@ElementList
	List<AbstractAppender> AppenderList;

	public AppenderConfiguration(){
		AppenderList = new ArrayList<AbstractAppender>();
	}
	
	public List<AbstractAppender> getAppenderList(){
		return AppenderList;
	}
	
	public void AddAppender(AbstractAppender Appender){
		AppenderList.add(Appender);
	}
	public boolean DeleteAppender(String AppenderName){
		
		Iterator<AbstractAppender> iter = AppenderList.iterator();
		
		while(iter.hasNext()){
			AbstractAppender appender = iter.next();
			if(AppenderName.equalsIgnoreCase(appender.GetAppenderName())){
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