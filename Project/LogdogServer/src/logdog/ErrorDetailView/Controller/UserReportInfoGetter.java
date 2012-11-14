package logdog.ErrorDetailView.Controller;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import logdog.Common.ServiceType;
import logdog.Common.BlobStore.BlobFileWriter;
import logdog.Common.BlobStore.BlobWriterFactory;
import logdog.Common.DataStore.PMF;
import logdog.DashBoard.DTO.Json.Highcharts.DayReport;
import logdog.ErrorDetailView.DTO.ErrorTypeData;
import logdog.ErrorDetailView.DTO.JqGrid.CallStackReport;
import logdog.ErrorDetailView.DTO.JqGrid.UserReportinfo;
import logdog.ErrorDetailView.DTO.JqGrid.UserSummaryData;
import logdog.ErrorReport.DAO.ErrorReportInfo;
import logdog.ErrorReport.DAO.ErrorTypeInfo;

import com.google.appengine.api.blobstore.BlobKey;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.gson.Gson;

/**
 * 	Detail한 에러리포트 정보를 만들기 위해 UserReport를 얻어와 Web에 맞는 Json 객체를 리턴한다.
 * @since 2012. 11. 15.오전 7:04:58
 * TODO
 * @author Karuana
 */
public class UserReportInfoGetter {

	
	/**
	 *	지정한 Key에 대한 에러 타입 정보들을 얻어온다. 
	 * @since 2012. 11. 15.오전 7:05:54
	 * TODO
	 * @author Karuana
	 * @param reportKey
	 * @return Json -> ErrorTypeData
	 */
	public String getErrorTypeInfo(String reportKey)
	{
		PersistenceManager	jdoConnector = PMF.getPMF().getPersistenceManager();
		ErrorTypeData info=null;
		try{
			
			ErrorTypeInfo targetReport = jdoConnector.getObjectById(ErrorTypeInfo.class, KeyFactory.stringToKey(reportKey));
			info = new ErrorTypeData(targetReport);
			Gson gson = new Gson();
			
			return gson.toJson(info);
		}
		catch(Exception e){
				
			e.printStackTrace();
			return null;
		}
		finally{
			jdoConnector.close();
			
		}
		
		
	}
	
	/**
	 *	CallStack 정보를 JqGrid 출력을 위한 Json으로 만들어 리턴한다.
	 * @since 2012. 11. 15.오전 7:06:38
	 * TODO
	 * @author Karuana
	 * @param reportKey
	 * @return json -> CallStackReport
	 */
	public String getCallsatckInfo(String reportKey)
	{
		PersistenceManager	jdoConnector = PMF.getPMF().getPersistenceManager();
		CallStackReport info=null;
		try{
			
			ErrorTypeInfo targetReport = jdoConnector.getObjectById(ErrorTypeInfo.class, KeyFactory.stringToKey(reportKey));
			info = new CallStackReport();
			List<String> call = targetReport.getCallstack();
			for(int i=0;i<call.size();i++)
			{
				info.addcallstack(call.get(i));
			}
			info.setRecodes(call.size());
			Gson gson = new Gson();
			
			return gson.toJson(info);
		}
		catch(Exception e){
				
			e.printStackTrace();
			return null;
		}
		finally{
			jdoConnector.close();
			
		}

	}
	
	/**
	 *	해당 에러의 User Report 정보 리스트의 요약본을 Json으로 변환하여 리턴한다.
	 * @since 2012. 11. 15.오전 7:12:23
	 * TODO
	 * @author Karuana
	 * @param reportKey
	 * @return json -> UserSummaryData
	 */
	public String getUserReportList(String reportKey)
	{
		PersistenceManager	jdoConnector = PMF.getPMF().getPersistenceManager();
		try{
		    String queryStr = String.format("select from " + ErrorReportInfo.class.getName() + 
		            " where E_ClassificationCode == '%s'", reportKey);
			
		    final Key key = KeyFactory.stringToKey(reportKey);
		    Query SearchQuery = jdoConnector.newQuery(ErrorReportInfo.class);
		    SearchQuery.setFilter("E_ClassificationCode == key");
		    SearchQuery.declareParameters("com.google.appengine.api.datastore.Key key");
		    List<ErrorReportInfo> users = (List<ErrorReportInfo>) SearchQuery.execute(key);
			
			UserSummaryData reportData = new UserSummaryData();
			System.out.println(users.size());
			
			for(int i=0;i<users.size();i++)
			{
				reportData.addSummaryData(users.get(i).getSummary(),users.get(i).getE_ReportCode());
			}
			reportData.setRecodes(users.size());
			Gson gson = new Gson();
			
			return gson.toJson(reportData);
		}catch(Exception e)
		{
			e.printStackTrace();
		
		}finally{
			jdoConnector.close();
			
		}
		
		return null;
	}
	
	/**
	 *	선택한 UserReport의 좀더 자세한 정보를 가져온다. 여기에는 로그데이터 blob이 포함된다.
	 * @since 2012. 11. 15.오전 7:13:56
	 * TODO
	 * @author Karuana
	 * @param reportKey -> 주의: 여기서의 키는 특정 에러리포트를 가르키는 키이다.
	 * @return json -> UserReportinfo
	 */
	public String getUserDetailReport(String reportKey)
	{
		PersistenceManager	jdoConnector = PMF.getPMF().getPersistenceManager();
		try{
		    final Key key = KeyFactory.stringToKey(reportKey);
		    ErrorReportInfo targetReport = jdoConnector.getObjectById(ErrorReportInfo.class, KeyFactory.stringToKey(reportKey));
			
		    UserReportinfo reportData = new UserReportinfo();
		    reportData.addReport(targetReport.getUserData(), targetReport.getLogBolbKey());	
			reportData.setRecodes(1);
			
			Gson gson = new Gson();
			
			return gson.toJson(reportData);
		}catch(Exception e)
		{
			e.printStackTrace();
		
		}finally{
			jdoConnector.close();
			
		}
		return null;
	}	
	
	/**
	 *	에러에 대한 일일 변화 그래프를 그리기 위한 정보를 json 형태로 가져온다.
	 * @since 2012. 11. 15.오전 7:20:46
	 * TODO
	 * @author Karuana
	 * @param reportKey
	 * @return json-> DayReport
	 */
	public String getDayVariation(String reportKey)
	{
		PersistenceManager	jdoConnector = PMF.getPMF().getPersistenceManager();
	    Query SearchQuery = jdoConnector.newQuery(ErrorReportInfo.class);
		try{
		    String queryStr = String.format("select from " + ErrorReportInfo.class.getName() + 
		            " where E_ClassificationCode == '%s'", reportKey);
			
		    final Key key = KeyFactory.stringToKey(reportKey);
	
		    SearchQuery.setFilter("E_ClassificationCode == key");
		    SearchQuery.declareParameters("com.google.appengine.api.datastore.Key key");
		    SearchQuery.setOrdering("TimeCode descending");	
		    List<ErrorReportInfo> users = (List<ErrorReportInfo>) SearchQuery.execute(key);
			
	
			int prevCode=(users.size() >0) ?users.get(0).getSummary().getTimeCode():0;
			int Count=1;
			DayReport report = new DayReport();
			for(int i=1;i<users.size();i++)
			{
				if(prevCode !=users.get(i).getSummary().getTimeCode())
				{
					report.AddDay(prevCode);
					report.AddReportRate(Count);
					prevCode=users.get(i).getSummary().getTimeCode();
					Count=1;
				}
				Count++;

			}
			report.AddDay(prevCode);
			report.AddReportRate(Count);
		
			Gson gson = new Gson();
			
			return gson.toJson(report);
		}catch(Exception e)
		{
			
			e.printStackTrace();
			return null;
			
		}finally{
			SearchQuery.closeAll();
			jdoConnector.close();
			
		}
		
	}	
	/**
	 * 에러의 상태를 디버그 완료 상태로 전환한다. 
	 * @since 2012. 11. 15.오전 7:23:45
	 * TODO
	 * @author Karuana
	 * @param reportKey
	 */
	public void onBugDataClear(String reportKey)
	{
		PersistenceManager	jdoConnector = PMF.getPMF().getPersistenceManager();
		try{
			ErrorTypeInfo targetReport = jdoConnector.getObjectById(ErrorTypeInfo.class, KeyFactory.stringToKey(reportKey));
			targetReport.setBugClear(true);
		
		}catch(Exception e)
		{
			
			e.printStackTrace();
			
		}finally{
			jdoConnector.close();
			
		}
	}
}
