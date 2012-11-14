package logdog.ErrorListViewer.Controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import logdog.Common.TimeUtil;
import logdog.Common.DataStore.PMF;
import logdog.ErrorListViewer.DTO.JqGrid.ErrorTypeReport;
import logdog.ErrorReport.DAO.ErrorReportInfo;
import logdog.ErrorReport.DAO.ErrorTypeInfo;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.gson.Gson;

public class ErrorTypeReportGetter {

	/**
	 *	제공된 클래스 이름을 바탕으로 해당 클래스에서 발생한 에러들을 찾아서 Json으로 리턴해준다.
	 * @since 2012. 11. 13.오전 2:58:50
	 * TODO	
	 * @author Karuana
	 * @param className
	 * @return
	 */
	public String getClassErrorReport(String className)
	{
		PersistenceManager jdoConnector = PMF.getPMF().getPersistenceManager();

		Query SearchQuery = jdoConnector.newQuery(ErrorTypeInfo.class);
		List<ErrorTypeInfo> ErrorTypeResults=null;


		try{

			SearchQuery.setFilter("OccurrenceClass == classname");
			SearchQuery.declareParameters("String classname");
			ErrorTypeResults = (List<ErrorTypeInfo>) 
								SearchQuery.execute(className);
				

			Iterator<ErrorTypeInfo> iterator = ErrorTypeResults.iterator();
			
			ErrorTypeReport report = new ErrorTypeReport();  
			report.setRecodes(ErrorTypeResults.size());
			
			while ( iterator.hasNext() ){
				ErrorTypeInfo info = iterator.next();
				HashMap<String,Object> error = new HashMap<String,Object>();
				error.put("errname",info.getErrorName());
				error.put("classname", info.getOccurrenceClass());
				error.put("line",info.getCodeLine());
				error.put("day", TimeUtil.GetTime2String(info.getLastUpdateDay()));
				error.put("total", info.getTotalOccurrences());
				error.put("clear", info.isBugClear());
				error.put("key", KeyFactory.keyToString(info.getE_ClassificationCode()));
				report.addError(error);	
			  }

			
			Gson gson = new Gson();
			return gson.toJson(report);
		}
		catch(Exception e){
					
			e.printStackTrace();
			return null;
		}
		finally{
			SearchQuery.closeAll();
			jdoConnector.close();
				
		}
		
	}
	
	/**
	 * 특정 날짜에 발생한 에러목록을 검출해주는 코
	 * @since 2012. 11. 13.오전 4:22:58
	 * TODO	
	 * @author Karuana
	 * @param Year
	 * @param DayCode
	 * @return
	 */
	public String getDayErrorReport(int Year, int DayCode)
	{
		PersistenceManager jdoConnector = PMF.getPMF().getPersistenceManager();
		Query SearchReport = jdoConnector.newQuery(ErrorReportInfo.class);

		List<ErrorReportInfo> ErrorReportList=null;



		try{
	
			
			SearchReport.setFilter("YearCode == year && TimeCode == day");
			SearchReport.declareParameters("int year,int day");
			ErrorReportList = (List<ErrorReportInfo>) 
					SearchReport.execute(Year,DayCode);
			
				

			Iterator<ErrorReportInfo> iterator = ErrorReportList.iterator();
			ArrayList<Key> KeyList = new ArrayList<Key>();
	
			while ( iterator.hasNext() ){
				ErrorReportInfo info = iterator.next();
				if(!KeyList.contains(info.getE_ClassificationCode()))
				{
					if(info.getE_ClassificationCode()!=null)
						KeyList.add(info.getE_ClassificationCode());
				}
			  }
			
			ErrorTypeReport report = new ErrorTypeReport();  
			for(int i=0;i<KeyList.size();i++)
			{
				ErrorTypeInfo info = jdoConnector.getObjectById(ErrorTypeInfo.class, KeyList.get(i));
				HashMap<String,Object> error = new HashMap<String,Object>();
				error.put("errname",info.getErrorName());
				error.put("classname", info.getOccurrenceClass());
				error.put("line",info.getCodeLine());
				error.put("day", TimeUtil.GetTime2String(info.getLastUpdateDay()));
				error.put("total", info.getTotalOccurrences());
				error.put("clear", info.isBugClear());
				error.put("key", KeyFactory.keyToString(info.getE_ClassificationCode()));
				report.addError(error);
			}
			Gson gson = new Gson();
			return gson.toJson(report);
		}
		catch(Exception e){
					
			e.printStackTrace();
			return null;
		}
		finally{
			SearchReport.closeAll();
			jdoConnector.close();
				
		}
		
	}
	
	public String getVersionErrorReport(String AppVersion, String OSVersion)
	{
		PersistenceManager jdoConnector = PMF.getPMF().getPersistenceManager();
		Query SearchReport = jdoConnector.newQuery(ErrorReportInfo.class);

		List<ErrorReportInfo> ErrorReportList=null;



		try{
	
			
			SearchReport.setFilter("AppVersion == app && OSVersion == os");
			SearchReport.declareParameters("String app,String os");
			ErrorReportList = (List<ErrorReportInfo>) 
					SearchReport.execute(AppVersion,OSVersion);
			
				

			Iterator<ErrorReportInfo> iterator = ErrorReportList.iterator();
			ArrayList<Key> KeyList = new ArrayList<Key>();
	
			while ( iterator.hasNext() ){
				ErrorReportInfo info = iterator.next();
				if(!KeyList.contains(info.getE_ClassificationCode()))
					KeyList.add(info.getE_ClassificationCode());
			  }
			
			ErrorTypeReport report = new ErrorTypeReport();  
			for(int i=0;i<KeyList.size();i++)
			{
				ErrorTypeInfo info = jdoConnector.getObjectById(ErrorTypeInfo.class, KeyList.get(i));
				HashMap<String,Object> error = new HashMap<String,Object>();
				error.put("errname",info.getErrorName());
				error.put("classname", info.getOccurrenceClass());
				error.put("line",info.getCodeLine());
				error.put("day", TimeUtil.GetTime2String(info.getLastUpdateDay()));
				error.put("total", info.getTotalOccurrences());
				error.put("clear", info.isBugClear());
				error.put("key", KeyFactory.keyToString(info.getE_ClassificationCode()));
				report.addError(error);
			}
			Gson gson = new Gson();
			return gson.toJson(report);
		}
		catch(Exception e){
					
			e.printStackTrace();
			return null;
		}
		finally{
			SearchReport.closeAll();
			jdoConnector.close();
				
		}
		
	}
	
}
