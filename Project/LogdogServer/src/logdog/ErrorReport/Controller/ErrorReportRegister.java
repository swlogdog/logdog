package logdog.ErrorReport.Controller;

import java.util.List;

import javax.jdo.PersistenceManager;

import logdog.Common.DataStore.PMF;
import logdog.ErrorReport.DAO.ErrorReportInfo;
import logdog.ErrorReport.DTO.ClientReportData;
import logdog.ErrorReport.DTO.ErrorUniqueID;
import logdog.ErrorReport.DTO.UserSummaryInfo;

import com.google.appengine.api.blobstore.BlobKey;
import com.google.appengine.api.datastore.Key;

/**
 * 	입력 받은 정보를 DataStore에 기록하는 Controller
 * @since 2012. 11. 19.오전 7:06:02
 * TODO
 * @author Karuana
 */
public class ErrorReportRegister {
	


	
	public ErrorReportRegister() {
		super();

		
	}
	
	/**
	 *	TestCase용 삭제 예정 
	 * @since 2012. 11. 17.오후 7:39:00
	 * TODO
	 * @author Karuana
	 * @param reportInfo
	 * @return
	 */
	public Key insertTestErrorReport(ClientReportData reportInfo)
	{
		PersistenceManager jdoConnector = PMF.getPMF().getPersistenceManager();
		
		ErrorReportInfo eInfo = new ErrorReportInfo(reportInfo,true);
		
		try{
				jdoConnector.makePersistent(eInfo);
		}
		catch(Exception e){
				e.printStackTrace();
			return null;
				
		}
		finally{
		
			jdoConnector.close();
			
		}
	
		return eInfo.getE_ReportCode();
	}

	/**
	 *	유저 에러 리포트 정보를 등록한다.
	 * @since 2012. 11. 19.오전 7:06:48
	 * TODO
	 * @author Karuana
	 * @param reportInfo
	 * @return	Key -> 등록된 유저 에러 리포트 키를 리턴한다.
	 */
	public Key insertErrorReport(ClientReportData reportInfo)
	{
		PersistenceManager jdoConnector = PMF.getPMF().getPersistenceManager();
		
		ErrorReportInfo eInfo = new ErrorReportInfo(reportInfo);
		
		try{
				jdoConnector.makePersistent(eInfo);
		}
		catch(Exception e){
				
			return null;
				
		}
		finally{
		
			jdoConnector.close();
			
		}
	
		return eInfo.getE_ReportCode();
	}
	/**
	 * 에러를 식별하는 기본 값과 레포트 키를 입력받아 타입 매칭을 실시하여 유저 레포트에 에러 타입 키를 입력한다.		
	 * @since 2012. 11. 2.오후 12:43:20
	 * TODO 에러 파일의 타입을 매칭한다.
	 * @author Karuana
	 * @param reportKey	유저 리포트 키 
	 * @param uid	에러를 식별하게 해주는 기본 값
	 */
	public Key MatchingErrorType(Key reportKey, ErrorUniqueID uid)
	{
		PersistenceManager	jdoConnector = PMF.getPMF().getPersistenceManager();
		Key ErrTypeKey =null;
		try{
			//System.out.print(reportKey);
			
		 	ErrorTypeClassifier TypeClassifier = new ErrorTypeClassifier();
		 	ErrTypeKey = TypeClassifier.UpdateErrorType(uid);
				
			ErrorReportInfo targetReport = jdoConnector.getObjectById(ErrorReportInfo.class, reportKey);
			targetReport.setE_ClassificationCode(ErrTypeKey);
			
		//	System.out.print(targetReport.getE_ClassificationCode());
		}
		catch(Exception e){
				
			e.printStackTrace();
				
		}
		finally{
			
			jdoConnector.close();
			
		}
		return ErrTypeKey;
	
	}
	/**
	 *	로크 파일의 키를 유저 리포트 정보에 입력한다.
	 * @since 2012. 11. 19.오전 7:15:06
	 * TODO
	 * @author Karuana
	 * @param reportKey
	 * @param filekey
	 */
	public void MatchingLogFile(Key reportKey, BlobKey filekey)
	{
		PersistenceManager	jdoConnector = PMF.getPMF().getPersistenceManager();

		try{
			
			ErrorReportInfo targetReport = jdoConnector.getObjectById(ErrorReportInfo.class, reportKey);
			targetReport.setLogBolbKey(filekey);
		}
		catch(Exception e){
				
			
				
		}
		finally{
			jdoConnector.close();
			
		}
	}
	
	/**
	 *	 요약 정보를 얻어온다.
	 * @since 2012. 11. 19.오전 7:16:45
	 * TODO
	 * @author Karuana
	 * @param reportKey
	 * @return
	 */
	public UserSummaryInfo getSummaryInfo(Key reportKey)
	{
		PersistenceManager	jdoConnector = PMF.getPMF().getPersistenceManager();
		UserSummaryInfo summaryInfo = null;
		try{
			
			ErrorReportInfo targetReport = jdoConnector.getObjectById(ErrorReportInfo.class, reportKey);
			summaryInfo=targetReport.getSummary();
		}
		catch(Exception e){
				
			
				
		}
		finally{
			jdoConnector.close();
			
		}
		return summaryInfo;
	}
	
	
}
