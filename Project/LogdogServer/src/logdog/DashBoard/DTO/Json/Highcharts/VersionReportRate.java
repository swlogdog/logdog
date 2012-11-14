package logdog.DashBoard.DTO.Json.Highcharts;

import java.util.ArrayList;
import java.util.Iterator;

import logdog.ErrorReport.DAO.AppVesionInfo;

public class VersionReportRate {
	private ArrayList<String> AppVersions;
	private ArrayList<OSVesionErrorRate> OSErrors;
	public VersionReportRate() {
		super();
		// TODO Auto-generated constructor stub
		AppVersions = new ArrayList<String> ();
		OSErrors =new ArrayList<OSVesionErrorRate>();
		
	}
	
	public void addAppVersion(String AppV)
	{
		AppVersions.add(AppV);
	}
	
	public void addOSerror(String OsVersion, int Count,int rate)
	{	
		Iterator<OSVesionErrorRate> It = OSErrors.iterator();
		boolean isVer =false;
		while ( It.hasNext() ){
			OSVesionErrorRate info = It.next();
			if(info.getOsVersion() == OsVersion)
			{
				
				info.setRate(Count, rate);
				isVer = true;
				break;
			}
			
		  }
	 
		
		if(!isVer)
		{
			OSVesionErrorRate Version = new OSVesionErrorRate(OsVersion);
			Version.initRate(AppVersions.size());
			Version.setRate(Count,rate);	
			//중간에 빠진 경우는 어쩔?
			OSErrors.add(Version);

		}
		
	}
	

}
