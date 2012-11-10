package logdog.DashBoard.Controller;

import java.util.Iterator;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import com.google.gson.Gson;

import logdog.Common.TimeUtil;
import logdog.Common.DataStore.PMF;
import logdog.DashBoard.DAO.DayReportInfo;
import logdog.DashBoard.DTO.Json.DayReport;

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
			while ( iterator.hasNext() ){
				DayReportInfo info = iterator.next();
				report.AddDay(info.getMDay());
				report.AddReportRate(info.getTotalOccurrences());
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
	
	public String getClassErrorRate()
	{
		PersistenceManager jdoConnector = PMF.getPMF().getPersistenceManager();

		Query SearchQuery = jdoConnector.newQuery(DayReportInfo.class);
		List<DayReportInfo> ErrorTypeResults=null;


		try{
			
				
			SearchQuery.setFilter("Year == year && MDay <= Timecode");
			SearchQuery.declareParameters("int year,int Timecode");
			SearchQuery.setOrdering("MDay ascending");	
			
			int YearCode = TimeUtil.GetNowYear();
			int TimeCode = TimeUtil.GetNowTimeCode();
			
			ErrorTypeResults = (List<DayReportInfo>) 
								SearchQuery.execute(YearCode, TimeCode);
			
			Iterator<DayReportInfo> iterator = ErrorTypeResults.iterator();
			DayReport report = new DayReport(); 
			while ( iterator.hasNext() ){
				DayReportInfo info = iterator.next();
				int Month = info.getMDay()/100;
				int day = info.getMDay()%100;

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
}
