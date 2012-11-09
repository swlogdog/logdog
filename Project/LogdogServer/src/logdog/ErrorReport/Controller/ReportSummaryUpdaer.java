package logdog.ErrorReport.Controller;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import logdog.Common.DataStore.PMF;
import logdog.DashBoard.DAO.CountryReportInfo;
import logdog.DashBoard.DAO.DayReportInfo;
import logdog.DashBoard.DAO.VersionReportInfo;
import logdog.ErrorReport.DAO.AppVesionInfo;
import logdog.ErrorReport.DTO.UserSummaryInfo;

public class ReportSummaryUpdaer {
	
	private boolean UpdateVersionError(final UserSummaryInfo reportInfo)
	{

		PersistenceManager jdoConnector = PMF.getPMF().getPersistenceManager();
	
		Query SearchQuery = jdoConnector.newQuery(VersionReportInfo.class);
		List<VersionReportInfo> VSummary=null;
		

		try{	
			VSummary = (List<VersionReportInfo>) SearchQuery.execute();
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
			DSummary = (List<DayReportInfo>) SearchQuery.execute();
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
			CSummary = (List<CountryReportInfo>) SearchQuery.execute();
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
	
	public void ChkAppVersion(String AppV)
	{
		PersistenceManager jdoConnector = PMF.getPMF().getPersistenceManager();
		
		Query SearchQuery = jdoConnector.newQuery(AppVesionInfo.class);
		List<AppVesionInfo> CSummary=null;
		

		try{	
			CSummary = (List<AppVesionInfo>) SearchQuery.execute();
			if(CSummary.size()==0)
			{
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
	
	public void UpdatedReportError(UserSummaryInfo reportInfo)
	{
		UpdateVersionError(reportInfo);
		UpdateDayReportInfo(reportInfo);
		UpdateCountryReportInfo(reportInfo);
	}
}
