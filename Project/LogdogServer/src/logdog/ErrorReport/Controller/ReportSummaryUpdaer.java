package logdog.ErrorReport.Controller;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import logdog.Common.DataStore.PMF;
import logdog.ErrorReport.DAO.AppVesionInfo;
import logdog.ErrorReport.DAO.VersionReportInfo;
import logdog.ErrorReport.DAO.Summary.ClassErrorInfo;
import logdog.ErrorReport.DAO.Summary.CountryReportInfo;
import logdog.ErrorReport.DAO.Summary.DayReportInfo;
import logdog.ErrorReport.DTO.UserSummaryInfo;

public class ReportSummaryUpdaer {
	
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
	
	public void UpdatedReportError(UserSummaryInfo reportInfo, String className)
	{
		UpdateVersionError(reportInfo);
		UpdateDayReportInfo(reportInfo);
		UpdateCountryReportInfo(reportInfo);
		UpdateClassError(className);
	}
}
