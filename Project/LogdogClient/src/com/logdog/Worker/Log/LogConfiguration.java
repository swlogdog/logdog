package com.logdog.Worker.Log;

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
	public LogConfiguration() {
		// TODO Auto-generated constructor stub
	}
	public void SetLevel(Level level){
		m_Level = level;
	}

	public void SetLevel(String levelstring){
		LevelString = levelstring;
		ConverterStringtoLevel();
	}
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
	public Level GetLevel(){
		return m_Level;
	}
}
