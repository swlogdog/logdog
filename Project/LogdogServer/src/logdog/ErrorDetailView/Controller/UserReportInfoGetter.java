package logdog.ErrorDetailView.Controller;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import logdog.Common.DataStore.PMF;
import logdog.DashBoard.DTO.Json.Highcharts.DayReport;
import logdog.ErrorDetailView.DTO.ErrorTypeData;
import logdog.ErrorDetailView.DTO.JqGrid.CallStackReport;
import logdog.ErrorDetailView.DTO.JqGrid.UserReportinfo;
import logdog.ErrorDetailView.DTO.JqGrid.UserSummaryData;
import logdog.ErrorReport.DAO.ErrorReportInfo;
import logdog.ErrorReport.DAO.ErrorTypeInfo;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.gson.Gson;

public class UserReportInfoGetter {

	
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
	
	public String getDayVariation(String reportKey)
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
			jdoConnector.close();
			
		}
		
	}	
}
