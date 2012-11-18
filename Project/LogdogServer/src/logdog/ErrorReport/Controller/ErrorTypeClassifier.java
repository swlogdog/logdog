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

/**
 * 	에러의 타입을 분류해주는 일종의 타입 분류기 클래스이다.
 * @since 2012. 11. 19.오전 7:17:36
 * TODO
 * @author Karuana
 */
public class ErrorTypeClassifier {

	
	public ErrorTypeClassifier() {
		super();
		
	}

	/**
	 *	입력된 uid값을 기준으로 해당 uid값과 일치하는 에러가 존재하는 체크하여 결과를 리턴한다.
	 * @since 2012. 11. 19.오전 7:17:51
	 * TODO
	 * @author Karuana
	 * @param errorTypeid
	 * @return boolean 에러타입이 존재하면 t, 아니면 f
	 */
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
	 *	새로운 에러 타입 정보를 추가한다. 추가한 뒤 추가한 에러 타입에 대한 정보를 리턴한다.
	 * @since 2012. 11. 2.오전 3:54:40
	 * TODO 
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
	
	/**
	 *	콜스택 정보를 에러 타입 정보에 기록한다. 
	 * @since 2012. 11. 19.오전 7:30:49
	 * TODO
	 * @author Karuana
	 * @param stackInfo
	 * @return
	 */
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
	 *	에러 타입이 존재하는 경우 에러 타입을 새로 추가하지 않고 정보를 새로 업데이트 한
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
	
	/**
	 *	에러 고유 아이디를 기준으로 에러타입에 대한 정보를 얻어온다.
	 * @since 2012. 11. 19.오전 7:32:52
	 * TODO
	 * @author Karuana
	 * @param errorTypeid
	 * @return ErrorTypeInfo
	 */
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
	
	/**
	 *	에러 리포트 키를 기준으로 에러 리포트 인포를 얻어온다.
	 * @since 2012. 11. 19.오전 7:33:37
	 * TODO
	 * @author Karuana
	 * @param ErrorKey
	 * @return	ErrorTypeInfo
	 */
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
	
	/**
	 * 에러 고유 정보를 기반으로 에러 타입의 Key를 얻어온다.
	 * @since 2012. 11. 19.오전 7:34:16
	 * TODO
	 * @author Karuana
	 * @param errorTypeid
	 * @return Key
	 */
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
