package com.logdog.Appender.AppEngine;


import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;


import com.logdog.Appender.FileAppender.FileAppender;
import com.logdog.ErrorReport.ClientReportData;
import com.logdog.common.Network.Network;



/**
 * 구글 앱엔진에 데이터를 보내는 어펜더
 * @since 2012. 11. 5.오후 10:08:22
 * TODO
 * @author JeongSeungsu
 */
@Root
public class AppEngineAppender extends FileAppender {

	@Element
	AppEngineCommunicator AppComunicator;
	
	Network	m_Network;

	public AppEngineAppender(){
		super();
	}
	public AppEngineAppender(String appendername, String savedirname, String logfilename, int readlogline,
							AppEngineCommunicator communicator){
		super(appendername, savedirname, logfilename, readlogline);
		AppComunicator = communicator;
	}


	public void InitAppender(Network network) {
		// TODO Auto-generated method stub
		super.InitAppender(network);
		m_Network = network;
		AppComunicator.SetSaveDir(super.GetSaveDirName());
		AppComunicator.SetSaveErrorReportFileName(super.GetErrorReportFileName());
		m_Network.AddCommunicator(AppComunicator);
	}
	
	public boolean ErrorReportProcess(ClientReportData Data) {
		if(!super.ErrorReportProcess(Data))
			return false;
		return true;
	}
	
	public String GetClassName() {
		// TODO Auto-generated method stub
		return this.GetClassName();
	}
	
}
