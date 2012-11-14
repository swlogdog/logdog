package logdog.DashBoard.Controller;

import java.util.Iterator;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import logdog.Common.TimeUtil;
import logdog.Common.DataStore.PMF;
import logdog.DashBoard.DTO.Json.Highcharts.ClassReportRate;
import logdog.DashBoard.DTO.Json.Highcharts.DayReport;
import logdog.DashBoard.DTO.Json.Highcharts.VersionReportRate;
import logdog.ErrorReport.DAO.AppVesionInfo;
import logdog.ErrorReport.DAO.ErrorTypeInfo;
import logdog.ErrorReport.DAO.VersionReportInfo;
import logdog.ErrorReport.DAO.Summary.ClassErrorInfo;
import logdog.ErrorReport.DAO.Summary.DayReportInfo;

import com.google.gson.Gson;

/**
 *  요청받은 그래프를 그리기 위한 정보를 DataStore를 수집하여 Json 객체로 만들어주는 Controller이다.
 * @since 2012. 11. 15.오전 6:22:27
 * TODO
 * @author Karuana
 */
public class SummaryGetter {

	/**
	 *	하이차트에 사용하는 데이터 형태로 최근 접소된 날짜별 에러 정보를 리턴한다.
	 *	리턴 데이터는 Json이며 최대 7일 기준으로 리턴된다.
	 * @since 2012. 11. 10.오전 2:01:45
	 * TODO
	 * @author Karuana
	 * @return Json
	 */
	public String getDayErrorRate()
	{
		PersistenceManager jdoConnector = PMF.getPMF().getPersistenceManager();

		Query SearchQuery = jdoConnector.newQuery(DayReportInfo.class);
		List<DayReportInfo> ErrorTypeResults=null;


		try{
			//최대 7개만 가져온다.
			SearchQuery.setFilter("Year == year && MDay <= Timecode");
			SearchQuery.declareParameters("int year,int Timecode");
			SearchQuery.setRange(0,6);
			SearchQuery.setOrdering("MDay descending");	
			
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
				
				int NonData= (prevData==null) ? TCode - info.getMDay() : prevData.getMDay() - info.getMDay()-1;//1일이상 차이나는지 체크
				int code = (prevData==null) ? TCode: info.getMDay();
				for(int i=0;i<NonData;i++)
				{
					report.AddDay(code-i);	//현재 TimeCode 연산을 단순 덧셈으로 하기 때문에 나중에 변경할 필요가 있다.
					report.AddReportRate(0);
				}
				
				report.AddDay(info.getMDay());
				report.AddReportRate(info.getTotalOccurrences());
				prevData = info;
			  }
			int StartDate = (prevData==null) ? TCode:prevData.getMDay();
			int j=1;
			for(int i=ErrorTypeResults.size();i<7;i++)
			{
				report.AddDay(StartDate-(j++));
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
	 *	class별 에러량을 조사하여 해당 그래프를 그리기위한 데이터를 Json으로 리턴한다. 
	 * 
	 * @since 2012. 11. 11.오전 9:14:06
	 * TODO
	 * @author Karuana
	 * @return Json
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
	 *	Version별 에러량을 체크하여 그래프를 그리기 위한 Json데이터로 만들어 리턴한다.
	 * @since 2012. 11. 11.오전 9:47:42
	 * TODO
	 * @author Karuana
	 * @return Json
	 */
	public String getVersionRate()
	{
		PersistenceManager jdoConnector = PMF.getPMF().getPersistenceManager();

		Query SearchQuery = jdoConnector.newQuery(AppVesionInfo.class);
		Query OSVSearch = jdoConnector.newQuery(VersionReportInfo.class);
		 
		List<AppVesionInfo> Versions=null;


		try{
			Versions = (List<AppVesionInfo>) 
								SearchQuery.execute();	//우선 서버에 저장된 App version 리스트를 가져온다.
			
			Iterator<AppVesionInfo> iterator = Versions.iterator();

			VersionReportRate Data = new VersionReportRate();
			Data.addAppVersion(Versions);
			int AppCount=0;
			while ( iterator.hasNext() ){
				AppVesionInfo info = iterator.next();
				List<VersionReportInfo> OSversion=null;
				OSVSearch.setFilter("AppVersion == ver");	// 각 App 버젼에 맞는 OS version에 에러량을 가져온다.
				OSVSearch.declareParameters("String ver");
			
				OSversion = (List<VersionReportInfo>) 
						OSVSearch.execute(info.getVersion());
				
				for(int i=0;i<OSversion.size();i++)
				{
					VersionReportInfo vinfo = OSversion.get(i);
					Data.addOSerror(vinfo.getOSVersion(), AppCount, vinfo.getTotalOccurrences());
				}
				
				AppCount++;
				
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
