package com.logdog.Log;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

import com.google.code.microlog4android.Level;

/**
 * 전역적인 로그 설정에 관한 모든것
 * @since 2012. 11. 12.오전 12:22:27
 * TODO
 * @author JeongSeungsu
 */
@Root(name = "Log")
public class LogConfiguration {

	@Element(name = "Level")
	String LevelString;
	
	Level	m_Level;
	/**
	 *
	 * @since 2012. 11. 15.오전 6:03:09
	 * TODO
	 * @author JeongSeungsu
	 */
	public LogConfiguration() {
		// TODO Auto-generated constructor stub
	}
	/**
	 * 레벨 설정
	 * @since 2012. 11. 15.오전 6:03:10
	 * TODO
	 * @author JeongSeungsu
	 * @param level
	 */
	public void SetLevel(Level level){
		m_Level = level;
	}

	/**
	 * 레벨 설정 String Data로 받아서 파싱
	 * @since 2012. 11. 15.오전 6:03:15
	 * TODO
	 * @author JeongSeungsu
	 * @param levelstring
	 */
	public void SetLevel(String levelstring){
		LevelString = levelstring;
		ConverterStringtoLevel();
	}
	/**
	 * 설정한 스트링 데이터를 파싱해준다.
	 * @since 2012. 11. 15.오전 6:03:26
	 * TODO
	 * @author JeongSeungsu
	 */
	public void ConverterStringtoLevel(){
		if(LevelString == null)
			return;
		String toLowerCase =  LevelString.toLowerCase();
		
		if(toLowerCase.equals("debug"))
			m_Level = Level.DEBUG;
		if(toLowerCase.equals("fatal"))
			m_Level = Level.FATAL;
		if(toLowerCase.equals("info"))
			m_Level = Level.INFO;
		if(toLowerCase.equals("warn"))
			m_Level = Level.WARN;
		if(toLowerCase.equals("error"))
			m_Level = Level.ERROR;
		
	}
	/**
	 * 레벨을 가져옴
	 * @since 2012. 11. 15.오전 6:03:38
	 * TODO
	 * @author JeongSeungsu
	 * @return
	 */
	public Level GetLevel(){
		return m_Level;
	}
}
