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
	
	/**
	 * 어펜더 리스트 반환
	 * @since 2012. 11. 15.오전 5:47:38
	 * TODO
	 * @author JeongSeungsu
	 * @return AppenderList를 반환
	 */
	public List<AbstractAppender> getAppenderList(){
		return AppenderList;
	}
	
	/**
	 * 현재 가지고 있는 Appender를 가져온다
	 * 
	 * @since 2012. 11. 15.오전 5:48:01
	 * TODO
	 * @author JeongSeungsu
	 * @param AppenderName Appender이름 값
	 * @return 있으면 객체를 없으면 Null값을
	 */
	public AbstractAppender GetAppender(String AppenderName){
		
		Iterator<AbstractAppender> iter = AppenderList.iterator();
		
		while(iter.hasNext()){
			AbstractAppender appender = iter.next();
			if(AppenderName.equalsIgnoreCase(appender.GetAppenderName())){
				return appender;
			}
		}
		return null;
	}
	
	/**
	 * 어펜더 추가
	 * @since 2012. 11. 15.오전 5:48:36
	 * TODO
	 * @author JeongSeungsu
	 * @param Appender 추가할 어펜더
	 */
	public void AddAppender(AbstractAppender Appender){
		AppenderList.add(Appender);
	}
	/**
	 * 어펜더 삭제
	 * @since 2012. 11. 15.오전 5:48:45
	 * TODO
	 * @author JeongSeungsu
	 * @param Appender 삭제할 어펜더
	 */
	public void RemoveAppender(AbstractAppender Appender){
		AppenderList.remove(Appender);
	}
	/**
	 * 어펜더 삭제
	 * @since 2012. 11. 15.오전 5:48:54
	 * TODO
	 * @author JeongSeungsu
	 * @param AppenderName 삭제할 어펜더 이름
	 */
	public void RemoveAppender(String AppenderName){
		
		Iterator<AbstractAppender> iter = AppenderList.iterator();
		
		while(iter.hasNext()){
			AbstractAppender appender = iter.next();
			if(AppenderName.equalsIgnoreCase(appender.GetAppenderName())){
				iter.remove();
				return;
			}
		}
	}
	
	/**
	 * 모든 어펜더 삭제
	 * @since 2012. 11. 15.오전 5:49:05
	 * TODO
	 * @author JeongSeungsu
	 */
	public void AllDeleteAppender(){
		AppenderList.clear();
	}
}