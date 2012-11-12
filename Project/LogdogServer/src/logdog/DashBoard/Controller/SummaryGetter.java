package logdog.DashBoard.Controller;

import java.util.Iterator;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import logdog.Common.TimeUtil;
import logdog.Common.DataStore.PMF;
import logdog.DashBoard.DTO.Json.ClassReportRate;
import logdog.DashBoard.DTO.Json.DayReport;
import logdog.DashBoard.DTO.Json.VersionReportRate;
import logdog.ErrorReport.DAO.AppVesionInfo;
import logdog.ErrorReport.DAO.ErrorTypeInfo;
import logdog.ErrorReport.DAO.VersionReportInfo;
import logdog.ErrorReport.DAO.Summary.ClassErrorInfo;
import logdog.ErrorReport.DAO.Summary.DayReportInfo;

import com.google.gson.Gson;

public class SummaryGetter {

	/**
	 *	하이차트에 사용하는 데이터 형태로 최근 접소된 날짜별 에러 정보를 리턴한다.
	 *	리턴 데이터는 Json이며 최대 7일 기준으로 리턴된다.
	 * @since 2012. 11. 10.오전 2:01:45
	 * TODO
	 * @author Karuana
	 * @return
	 */
	public String getDayErrorRate()
	{
		PersistenceManager jdoConnector = PMF.getPMF().getPersistenceManager();

		Query SearchQuery = jdoConnector.newQuery(DayReportInfo.class);
		List<DayReportInfo> ErrorTypeResults=null;


		try{

			SearchQuery.setFilter("Year == year && MDay <= Timecode");
			SearchQuery.declareParameters("int year,int Timecode");
			SearchQuery.setRange(0,6);
			SearchQuery.setOrdering("MDay ascending");	
			
			int YearCode = TimeUtil.GetNowYear();
			int TimeCode = TimeUtil.GetNowTimeCode();
			ErrorTypeResults = (List<DayReportInfo>) 
								SearchQuery.execute(YearCode, TimeCode);


			Iterator<DayReportInfo> iterator = ErrorTypeResults.iterator();
			
			DayReport report = new DayReport();  

			DayReportInfo prevData=null;
			int TCode = TimeUtil.GetNowTimeCode();//오늘의 타임코드를 얻어온다.
			while ( iterator.hasNext() ){
				DayReportInfo info = iterator.next();
				int NonData= (prevData==null) ? TCode - info.getMDay() : prevData.getMDay() - info.getMDay();
				int code = (prevData==null) ? TCode: prevData.getMDay();
				for(int i=0;i<NonData;i++)
				{
					report.AddDay(code-i);
					report.AddReportRate(0);
				}
				
				report.AddDay(info.getMDay());
				report.AddReportRate(info.getTotalOccurrences());
				prevData = info;
			  }
			int StartDate = (prevData==null) ? TCode:prevData.getMDay();
			for(int i=0+ErrorTypeResults.size();i<7;i++)
			{
				report.AddDay(StartDate-(i));
				report.AddReportRate(0);
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
	 *	검색에 문제가 있다. 현재 단순히 Error Type으로는 문제가 있으니 해결 방법을 생각해보자
	 * 가장 쉬운 해결책은 테이블을 하나 더 만들기.. 하지만 이에도 문제가 있다. 문제는 해당 클래스에서 발생한 에러를 검색할 때 문제가 있다.
	 * @since 2012. 11. 11.오전 9:14:06
	 * TODO
	 * @author Karuana
	 * @return
	 */
	public String getClassErrorRate()
	{
		PersistenceManager jdoConnector = PMF.getPMF().getPersistenceManager();

		Query SearchQuery = jdoConnector.newQuery(ClassErrorInfo.class);
		List<ClassErrorInfo> ErrorTypeResults=null;


		try{
			
				
			SearchQuery.setOrdering("TotalOccurrences descending");	
			
			ErrorTypeResults = (List<ClassErrorInfo>) 
								SearchQuery.execute();
			
			Iterator<ClassErrorInfo> iterator = ErrorTypeResults.iterator();
			ClassReportRate report = new ClassReportRate(); 

			while ( iterator.hasNext() ){
				ClassErrorInfo info = iterator.next();
				report.addClassRate(info.getOccurrenceClass(), info.getTotalOccurrences());
			  }
		 
			Gson gson = new Gson();
			return gson.toJson(report);
		}
		catch(Exception e){
					
			return null;
		}
		finally{
			SearchQuery.closeAll();
			jdoConnector.close();
				
		}
	}
	
	/**
	 *
	 * @since 2012. 11. 11.오전 9:47:42
	 * TODO
	 * @author Karuana
	 * @return
	 */
	public String getVersionRate()
	{
		PersistenceManager jdoConnector = PMF.getPMF().getPersistenceManager();

		Query SearchQuery = jdoConnector.newQuery(AppVesionInfo.class);
		Query OSVSearch = jdoConnector.newQuery(VersionReportInfo.class);
		 
		List<AppVesionInfo> Versions=null;


		try{
			Versions = (List<AppVesionInfo>) 
								SearchQuery.execute();
			
			Iterator<AppVesionInfo> iterator = Versions.iterator();

			VersionReportRate Data = new VersionReportRate();
			while ( iterator.hasNext() ){
				AppVesionInfo info = iterator.next();
				Data.addAppVersion(info.getVersion());
				List<VersionReportInfo> OSversion=null;
				OSVSearch.setFilter("AppVersion == ver");
				OSVSearch.declareParameters("String ver");
			
				OSversion = (List<VersionReportInfo>) 
						OSVSearch.execute(info.getVersion());
				
				for(int i=0;i<OSversion.size();i++)
				{
					VersionReportInfo vinfo = OSversion.get(i);
					Data.addOSerror(vinfo.getOSVersion(), i, vinfo.getTotalOccurrences());
				}
				
				
			  }
		 
			Gson gson = new Gson();
			return gson.toJson(Data);
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
	
}
