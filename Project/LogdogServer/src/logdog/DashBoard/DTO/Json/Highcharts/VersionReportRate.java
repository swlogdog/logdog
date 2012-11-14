package logdog.DashBoard.DTO.Json.Highcharts;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

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
	
	public void addAppVersion(List<AppVesionInfo>  AppV)
	{
		for(int i=0;i<AppV.size();i++)
			AppVersions.add(AppV.get(i).getVersion());
	}
	
	public void addOSerror(String OsVersion, int Count,int rate)
	{	
		Iterator<OSVesionErrorRate> It = OSErrors.iterator();
		boolean isVer =false;
		while ( It.hasNext() ){
			OSVesionErrorRate info = It.next();
			System.out.println(info.getOsVersion()+"    "+OsVersion);
			
			if(OsVersion.compareTo(info.getOsVersion())==0)
			{
				info.setRate(Count, rate);
				isVer = true;
				break;
			}
			
		  }
	 
		
		if(!isVer)
		{
			System.out.println("new ");
			OSVesionErrorRate Version = new OSVesionErrorRate(OsVersion);
			Version.initRate(AppVersions.size());
			Version.setRate(Count,rate);	
			//중간에 빠진 경우는 어쩔?
			OSErrors.add(Version);

		}
		
	}
	

}
