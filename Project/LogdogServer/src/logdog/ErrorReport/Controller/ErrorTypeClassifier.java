package logdog.ErrorReport.Controller;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.ws.rs.WebApplicationException;

import logdog.Common.DataStore.PMF;
import logdog.ErrorReport.DAO.ErrorReportInfo;
import logdog.ErrorReport.DAO.ErrorTypeInfo;
import logdog.ErrorReport.DTO.CallStackInfo;
import logdog.ErrorReport.DTO.ErrorUniqueID;

import com.google.appengine.api.datastore.Key;

public class ErrorTypeClassifier {

	
	public ErrorTypeClassifier() {
		super();
		
	}

	public boolean IsErrorType(ErrorUniqueID errorTypeid)
	{
	
		PersistenceManager jdoConnector = PMF.getPMF().getPersistenceManager();
		ErrorTypeInfo SearchError=null;
		
		Query SearchQuery = jdoConnector.newQuery(ErrorTypeInfo.class);
		List<ErrorTypeInfo> ErrorTypeResults=null;
		boolean isError=false;

		try{	
			SearchQuery.setFilter("ErrorName == errorId && OccurrenceClass == ClassName && codeLine == line");
			SearchQuery.declareParameters("String errorId,String ClassName, int line");
	
			ErrorTypeResults = (List<ErrorTypeInfo>) SearchQuery.execute(errorTypeid.getName(), errorTypeid.getClassname(),errorTypeid.getLine());
			isError = (ErrorTypeResults.size()==0) ? false: true; 
				
		}
		catch(Exception e){
				e.printStackTrace();
			throw new WebApplicationException(500);
		}
		finally{
			SearchQuery.closeAll();
			jdoConnector.close();
			
		}
		return isError;
	}
	
	/**
	 *
	 * @since 2012. 11. 2.오전 3:54:40
	 * TODO 키가 정상적으로 오는지 테스트할 것 옴 
	 * @author Karuana
	 * @param name
	 * @param classname
	 * @return
	 */
	public Key InsertErrorType(ErrorUniqueID errinfo)	
	{
		PersistenceManager jdoConnector = PMF.getPMF().getPersistenceManager();
		
		ErrorTypeInfo eType;
	
		try{
				eType = new ErrorTypeInfo(errinfo);
				jdoConnector.makePersistent(eType);
		}
		catch(Exception e){
				e.printStackTrace();
			return null;
				
		}
		finally{
		
			jdoConnector.close();
			
		}
		return eType.getE_ClassificationCode();
	}
	
	public boolean LinkCallStackData(CallStackInfo stackInfo)
	{
		PersistenceManager jdoConnector = PMF.getPMF().getPersistenceManager();
		
		ErrorTypeInfo SearchError=null;
			
		Query SearchQuery = jdoConnector.newQuery(ErrorTypeInfo.class);
		List<ErrorTypeInfo> ErrorTypeResults=null;
	

		try{		
			SearchQuery.setFilter("ErrorName == errorId && OccurrenceClass == ClassName && codeLine == line");
			SearchQuery.declareParameters("String errorId,String ClassName, int line");
	
	
			ErrorTypeResults = (List<ErrorTypeInfo>) 
								SearchQuery.execute(stackInfo.getName(), stackInfo.getClassname(),stackInfo.getLine());
		
			if(ErrorTypeResults.size()>0)
			{
				ErrorTypeResults.get(0).setCallstack(stackInfo.getCallstack());
			}
			
		}
		catch(Exception e){
					e.printStackTrace();
			return false;
		}
		finally{
			SearchQuery.closeAll();
			jdoConnector.close();
				
		}
		return true;
	}
	
	/**
	 *	Backend에서 사용하는 
	 * @since 2012. 11. 10.오전 9:50:51
	 * TODO
	 * @author Karuana
	 * @param errorTypeid
	 * @return
	 */
	public Key UpdateErrorType(ErrorUniqueID errorTypeid)
	{
		PersistenceManager jdoConnector = PMF.getPMF().getPersistenceManager();
		
		ErrorTypeInfo SearchError=null;
			
		Query SearchQuery = jdoConnector.newQuery(ErrorTypeInfo.class);
		List<ErrorTypeInfo> ErrorTypeResults=null;
		ErrorTypeInfo errType = null;

		try{
			
				
			SearchQuery.setFilter("ErrorName == errorId && OccurrenceClass == ClassName && codeLine == line");
			SearchQuery.declareParameters("String errorId,String ClassName, int line");

			ErrorTypeResults = (List<ErrorTypeInfo>) 
								SearchQuery.execute(errorTypeid.getName(), errorTypeid.getClassname(),errorTypeid.getLine());
			
			errType =ErrorTypeResults.get(0);
			errType.updateError();
		}
		catch(Exception e){
					e.printStackTrace();
			return null;
		}
		finally{
			SearchQuery.closeAll();
			jdoConnector.close();
				
		}
		
		return (errType == null) ? null: errType.getE_ClassificationCode();
	}
	
	public ErrorTypeInfo getErrorTypeInfo(ErrorUniqueID errorTypeid)
	{	
		PersistenceManager jdoConnector = PMF.getPMF().getPersistenceManager();
		
		ErrorTypeInfo SearchError=null;
		
		Query SearchQuery = jdoConnector.newQuery(ErrorTypeInfo.class);
		List<ErrorTypeInfo> ErrorTypeResults=null;


		try{
		
			
			SearchQuery.setFilter("ErrorName == errorId && OccurrenceClass == ClassName && codeLine == line");
			SearchQuery.declareParameters("String errorId,String ClassName, int line");
	
			ErrorTypeResults = (List<ErrorTypeInfo>) 
					SearchQuery.execute(errorTypeid.getName(), errorTypeid.getClassname(),errorTypeid.getLine());
				
		}
		catch(Exception e){
				
			e.printStackTrace();
		}
		finally{
			SearchQuery.closeAll();
			jdoConnector.close();
			
		}
		
		
		return (ErrorTypeResults.size()==0) ? null: ErrorTypeResults.get(0);
		
	}
	
	public final ErrorTypeInfo getErrorTypeInfo(Key ErrorKey)
	{
	
		PersistenceManager jdoConnector = PMF.getPMF().getPersistenceManager();
			
		ErrorTypeInfo eType =null;
			
		


		try{
			eType = jdoConnector.getObjectById(ErrorTypeInfo.class, ErrorKey);
			}
		catch(Exception e){
			e.printStackTrace();
			return null;
		}
		finally{
			
			jdoConnector.close();
				
		}
			
			
		return eType;
			
	}
	
	public final Key getErrorTypeKey(ErrorUniqueID errorTypeid)
	{
		PersistenceManager jdoConnector = PMF.getPMF().getPersistenceManager();
		
		ErrorTypeInfo SearchError=null;
			
		Query SearchQuery = jdoConnector.newQuery(ErrorTypeInfo.class);
		List<ErrorTypeInfo> ErrorTypeResults=null;
		Key ErrorKey=null;

		try{		
			SearchQuery.setFilter("ErrorName == errorId && OccurrenceClass == ClassName && codeLine == line");
			SearchQuery.declareParameters("String errorId,String ClassName, int line");
	
			ErrorTypeResults = (List<ErrorTypeInfo>) 
					SearchQuery.execute(errorTypeid.getName(), errorTypeid.getClassname(),errorTypeid.getLine());
		
			if(ErrorTypeResults.size()>0)
			{
				ErrorKey = ErrorTypeResults.get(0).getE_ClassificationCode();
			}
			
		}
		catch(Exception e){
					e.printStackTrace();
			
		}
		finally{
			SearchQuery.closeAll();
			jdoConnector.close();
				
		}
			
			
		return ErrorKey;
	}

}
