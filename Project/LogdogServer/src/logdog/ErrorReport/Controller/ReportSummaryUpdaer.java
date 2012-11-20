package logdog.ErrorReport.Controller;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import logdog.Common.TimeUtil;
import logdog.Common.DataStore.PMF;
import logdog.ErrorReport.DAO.AppVesionInfo;
import logdog.ErrorReport.DAO.Summary.ClassErrorInfo;
import logdog.ErrorReport.DAO.Summary.CountryReportInfo;
import logdog.ErrorReport.DAO.Summary.DayReportInfo;
import logdog.ErrorReport.DAO.Summary.MonthReportInfo;
import logdog.ErrorReport.DAO.Summary.VersionReportInfo;
import logdog.ErrorReport.DAO.Summary.WeekReportInfo;
import logdog.ErrorReport.DTO.UserSummaryInfo;

/**
 * HighCharts를 이용해 DashBoard에 요약 그래프를 그리기 위한 DB를 구성하는 Controller이다.
 * @since 2012. 11. 19.오전 7:35:59
 * TODO
 * @author Karuana
 */
public class ReportSummaryUpdaer {
	
	/**
	 *	들어온 에러에 대한 버젼 정보를 갱신한다.
	 * @since 2012. 11. 19.오전 7:37:33
	 * TODO
	 * @author Karuana
	 * @param reportInfo
	 * @return
	 */
	private boolean UpdateVersionError(final UserSummaryInfo reportInfo)
	{

		PersistenceManager jdoConnector = PMF.getPMF().getPersistenceManager();
	
		Query SearchQuery = jdoConnector.newQuery(VersionReportInfo.class);
		List<VersionReportInfo> VSummary=null;
		

		try{
			SearchQuery.setFilter("AppVersion == Aver && OSVersion == Over");
			SearchQuery.declareParameters("String Aver,String Over");
			
			VSummary = (List<VersionReportInfo>) SearchQuery.execute(reportInfo.getAppVersion(),reportInfo.getOSVersion());

			if(VSummary.size()>0)
			{
				VersionReportInfo vSummary = VSummary.get(0);
				vSummary.UpdatedError();			
			}
			else
			{
				VersionReportInfo vSummary = new VersionReportInfo(reportInfo.getAppVersion(),reportInfo.getOSVersion());
				jdoConnector.makePersistent(vSummary);
				ChkAppVersion(reportInfo.getAppVersion());
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
	 *	들어온 에러에 대한 날짜 정보를 갱신한다.
	 * @since 2012. 11. 19.오전 7:37:54
	 * TODO
	 * @author Karuana
	 * @param reportInfo
	 * @return
	 */
	private boolean UpdateDayReportInfo(final UserSummaryInfo reportInfo)
	{

		PersistenceManager jdoConnector = PMF.getPMF().getPersistenceManager();
	
		Query SearchQuery = jdoConnector.newQuery(DayReportInfo.class);
		List<DayReportInfo> DSummary=null;
		

		try{	
			SearchQuery.setFilter("Year == year && MDay == Timecode");
			SearchQuery.declareParameters("int year,int Timecode");
			
			DSummary = (List<DayReportInfo>) SearchQuery.execute(reportInfo.getYearCode(),reportInfo.getTimeCode());
			if(DSummary.size()>0)
			{
				DayReportInfo dSummary = DSummary.get(0);
				dSummary.UpdatedError();			
			}
			else
			{
				DayReportInfo dSummary = new DayReportInfo(reportInfo.getYearCode(),reportInfo.getTimeCode());
				jdoConnector.makePersistent(dSummary);
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
	 *	들어온 에러에 대한 주간 정보를 갱신한다.
	 * @since 2012. 11. 19.오전 7:38:11
	 * TODO
	 * @author Karuana
	 * @param reportInfo
	 * @return
	 */
	private boolean UpdateWeekReportInfo(final UserSummaryInfo reportInfo)
	{

		PersistenceManager jdoConnector = PMF.getPMF().getPersistenceManager();
	
		Query SearchQuery = jdoConnector.newQuery(WeekReportInfo.class);
		List<WeekReportInfo> WSummary=null;
		

		try{	
			SearchQuery.setFilter("Year == year && Week == Timecode");
			SearchQuery.declareParameters("int year,int Timecode");
			int weekcode =  TimeUtil.getWeekCode(TimeUtil.getCode2Date(reportInfo.getYearCode(),reportInfo.getTimeCode()));
			WSummary = (List<WeekReportInfo>) SearchQuery.execute(reportInfo.getYearCode(),weekcode);
			if(WSummary.size()>0)
			{
				WeekReportInfo wSummary = WSummary.get(0);
				wSummary.UpdatedError();			
			}
			else
			{
				
				WeekReportInfo wSummary = new WeekReportInfo(reportInfo.getYearCode(),weekcode);
				jdoConnector.makePersistent(wSummary);
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
	 *	들어온 에러에 대한 월간 정보를 갱신한다.
	 * @since 2012. 11. 19.오전 7:38:24
	 * TODO
	 * @author Karuana
	 * @param reportInfo
	 * @return
	 */
	private boolean UpdateMonthReportInfo(final UserSummaryInfo reportInfo)
	{

		PersistenceManager jdoConnector = PMF.getPMF().getPersistenceManager();
		
		Query SearchQuery = jdoConnector.newQuery(MonthReportInfo.class);
		List<MonthReportInfo> MSummary=null;
		

		try{	
			SearchQuery.setFilter("Year == year && Month == Timecode");
			SearchQuery.declareParameters("int year,int Timecode");
			int Month = reportInfo.getTimeCode()/100;
			MSummary = (List<MonthReportInfo>) SearchQuery.execute(reportInfo.getYearCode(),Month);
			if(MSummary.size()>0)
			{
				MonthReportInfo mSummary = MSummary.get(0);
				mSummary.UpdatedError();			
			}
			else
			{
				
				MonthReportInfo mSummary = new MonthReportInfo(reportInfo.getYearCode(),Month);
				jdoConnector.makePersistent(mSummary);
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
	 *	들어온 정보에 대한 국가 정보를 갱신한다.
	 * @since 2012. 11. 19.오전 7:38:40
	 * TODO
	 * @author Karuana
	 * @param reportInfo
	 * @return
	 */
	private boolean UpdateCountryReportInfo(final UserSummaryInfo reportInfo)
	{

		PersistenceManager jdoConnector = PMF.getPMF().getPersistenceManager();
	
		Query SearchQuery = jdoConnector.newQuery(CountryReportInfo.class);
		List<CountryReportInfo> CSummary=null;
		

		try{	
			SearchQuery.setFilter("CountryCode ==code");
			SearchQuery.declareParameters("String code");

			CSummary = (List<CountryReportInfo>) SearchQuery.execute(reportInfo.getCountryName());
			if(CSummary.size()>0)
			{
				CountryReportInfo cSummary = CSummary.get(0);
				cSummary.UpdatedError();			
			}
			else
			{
				CountryReportInfo cSummary = new CountryReportInfo(reportInfo.getCountryName());
				jdoConnector.makePersistent(cSummary);
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
	 *	해당 앱 버젼이 존재하는 버젼인지 신번젼인지 체크하고 신버젼이면 데이터를 갱신한다.
	 * @since 2012. 11. 19.오전 7:39:00
	 * TODO
	 * @author Karuana
	 * @param AppV
	 */
	private void ChkAppVersion(String AppV)
	{
		PersistenceManager jdoConnector = PMF.getPMF().getPersistenceManager();
		
		Query SearchQuery = jdoConnector.newQuery(AppVesionInfo.class);
		List<AppVesionInfo> CSummary=null;
		

		try{	
			SearchQuery.setFilter("Version == Aver");
			SearchQuery.declareParameters("String Aver");
			
			CSummary = (List<AppVesionInfo>) SearchQuery.execute(AppV);
			System.out.println("입력");
			if(CSummary==null || CSummary.size()==0)
			{
				System.out.println("입력");
				AppVesionInfo cSummary = new AppVesionInfo(AppV);
				jdoConnector.makePersistent(cSummary);
			}
		}
		catch(Exception e){
				
			e.printStackTrace();
		
		}
		finally{
			SearchQuery.closeAll();
			jdoConnector.close();
		}

	}
	
	/**
	 *	입력된 에러와 관련된 클래스 에러 정보를 갱신한다.
	 * @since 2012. 11. 19.오전 7:40:23
	 * TODO
	 * @author Karuana
	 * @param className
	 * @return
	 */
	private boolean UpdateClassError(String className)
	{
		PersistenceManager jdoConnector = PMF.getPMF().getPersistenceManager();
		
		Query SearchQuery = jdoConnector.newQuery(ClassErrorInfo.class);
		List<ClassErrorInfo> CSummary=null;
		

		try{	
			SearchQuery.setFilter("OccurrenceClass == classname");
			SearchQuery.declareParameters("String classname");

			CSummary = (List<ClassErrorInfo>) SearchQuery.execute(className);
			if(CSummary.size()>0)
			{
				ClassErrorInfo cSummary = CSummary.get(0);
				cSummary.UpdatedError();			
			}
			else
			{
				ClassErrorInfo cSummary = new ClassErrorInfo(className);
				jdoConnector.makePersistent(cSummary);
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
	 *	갱신 작업을 진행하는 클래스이다.
	 * @since 2012. 11. 19.오전 7:40:46
	 * TODO
	 * @author Karuana
	 * @param reportInfo
	 * @param className
	 */
	public void UpdatedReportError(UserSummaryInfo reportInfo, String className)
	{
		UpdateVersionError(reportInfo);
		UpdateDayReportInfo(reportInfo);
		UpdateWeekReportInfo(reportInfo);
		UpdateMonthReportInfo(reportInfo);
		UpdateCountryReportInfo(reportInfo);
		UpdateClassError(className);
	}
}
