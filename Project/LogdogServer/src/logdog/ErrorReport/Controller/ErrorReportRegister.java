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

public class ErrorReportRegister {
	


	
	public ErrorReportRegister() {
		super();

		
	}

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
	 *
	 * @since 2012. 11. 2.오후 12:43:20
	 * TODO 에러 파일의 타입을 매칭한다.
	 * @author Karuana
	 * @param reportKey
	 * @param uid
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
				
			System.out.print(e.getClass() +"    매칭 에  "+ e.getMessage());
				
		}
		finally{
			
			jdoConnector.close();
			
		}
		return ErrTypeKey;
	
	}
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
	
	public List<ErrorReportInfo> getErrorReport(Key ErrorType)
	{
		return null;
	}
	
	public boolean deleteErrorReport(Key reportKey)
	{
		return true;
	}	
	
}
