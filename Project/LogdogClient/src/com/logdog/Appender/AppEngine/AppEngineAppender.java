package com.logdog.Appender.AppEngine;


import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;


import android.util.Log;

import com.logdog.Appender.FileAppender.FileAppender;
import com.logdog.ErrorReport.ClientReportData;
import com.logdog.Formatter.IFormatter;
import com.logdog.common.File.FileControler;
import com.logdog.common.Network.Network;



/**
 * 구글 앱엔진에 데이터를 보내는 어펜더
 * FileAppender를 상속받아서 구현한다 
 * @since 2012. 11. 5.오후 10:08:22
 * TODO
 * @author JeongSeungsu
 */
@Root
public class AppEngineAppender extends FileAppender {

	
	
	/**
	 * AppEngine과의 소통을 담당하는 커뮤니 케이터
	 */
	@Element
	AppEngineCommunicator AppComunicator;
	

	
	/**
	 * Network객체에 등록하기 위한 더미
	 */
	Network	m_Network;

	/**
	 * 생성
	 * @since 2012. 11. 15.오전 5:52:27
	 * TODO
	 * @author JeongSeungsu
	 */
	public AppEngineAppender(){
		super();
	}
	/**
	 * 생성자
	 * @since 2012. 11. 15.오전 5:52:24
	 * TODO
	 * @author JeongSeungsu
	 * @param appendername 어펜더 이름
	 * @param savedirname 저장할 폴더 이름
	 * @param logfilename 저장할 로그파일이름
	 * @param readlogline Sending할때 보낼 로그 라인수 
	 * @param communicator 어떻게 보낼지를 가지고 있는 커뮤니케이터
	 * @param formatter 포맷 설정 객체
	 */
	public AppEngineAppender(String appendername, String savedirname, String logfilename, int readlogline,
							AppEngineCommunicator communicator,IFormatter formatter){
		super(appendername, savedirname, logfilename, readlogline, formatter);
		AppComunicator = communicator;
	}


	public void InitAppender(Network network) {
		// TODO Auto-generated method stub
		super.InitAppender(network);
		m_Network = network;
		AppComunicator.SetSavedDir(super.GetSaveDirName());
		AppComunicator.SetSavedErrorReportFileName(super.GetErrorReportFileName());
		m_Network.AddCommunicator(AppComunicator);
	}
	
	public boolean ErrorReportProcess(ClientReportData Data) {
		if(!super.ErrorReportProcess(Data))
			return false;
			
		return true;
	}
	
	/**
	 * 가지고 있는 커뮤니케이터를 반환한다.
	 * @since 2012. 11. 15.오전 5:52:36
	 * TODO
	 * @author JeongSeungsu
	 * @return
	 */
	public AppEngineCommunicator GetCommunicator(){
		return AppComunicator;
	}
}
