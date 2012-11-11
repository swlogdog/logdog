package logdog.DashBoard.DTO.Json;

import java.util.ArrayList;

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
	
	public void addOSerror(OSVesionErrorRate OSError)
	{
		OSErrors.add(OSError);
	}
}
