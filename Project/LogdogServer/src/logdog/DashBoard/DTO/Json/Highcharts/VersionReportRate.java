package logdog.DashBoard.DTO.Json.Highcharts;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import logdog.ErrorReport.DAO.AppVesionInfo;

/**
 *  App 및 OS 버젼별 에러 그래프를 그리기 위한 데이터들을 저장하고 있는 객체이다.
 *  이 객체를 기반으로 highchart에서 사용할 Json데이터를 생성한다. 
 * @since 2012. 11. 15.오전 6:38:42
 * TODO
 * @author Karuana
 */
public class VersionReportRate {
	/**
	 * APP 버젼 목록들을 저장해둔다.
	 */
	private ArrayList<String> AppVersions;
	/**
	 * OS 버젼과 에러량을 저장하는 OSVersionErrorRate 객체를 리스트로 가지고 있다.
	 */
	private ArrayList<OSVesionErrorRate> OSErrors;
	/**
	 * 생성자 
	 * @since 2012. 11. 15.오전 6:42:40
	 * TODO
	 * @author Karuana
	 */
	public VersionReportRate() {
		super();
		// TODO Auto-generated constructor stub
		AppVersions = new ArrayList<String> ();
		OSErrors =new ArrayList<OSVesionErrorRate>();
		
	}
	/**
	 *	AppVersion을 추가한다.
	 * @since 2012. 11. 15.오전 6:42:59
	 * TODO
	 * @author Karuana
	 * @param AppV
	 */
	public void addAppVersion(List<AppVesionInfo>  AppV)
	{
		for(int i=0;i<AppV.size();i++)
			AppVersions.add(AppV.get(i).getVersion());
	}
	
	/**
	 * OS Version과 에러량을 추가한다.
	 * @since 2012. 11. 15.오전 6:43:18
	 * TODO
	 * @author Karuana
	 * @param OsVersion
	 * @param Count
	 * @param rate
	 */
	public void addOSerror(String OsVersion, int Count,int rate)
	{	
		Iterator<OSVesionErrorRate> It = OSErrors.iterator();
		boolean isVer =false;
		while ( It.hasNext() ){
			OSVesionErrorRate info = It.next();
			System.out.println(info.getOsVersion()+"    "+OsVersion);
			
			if(OsVersion.compareTo(info.getOsVersion())==0)	// 두문자가 같은지 비교 
			{
				info.setRate(Count, rate);
				isVer = true;
				break;
			}
			
		  }
	 
		
		if(!isVer)//같은 요소가 없다면 초기화한뒤 추가 
		{
			OSVesionErrorRate Version = new OSVesionErrorRate(OsVersion);
			Version.initRate(AppVersions.size());
			Version.setRate(Count,rate);	
			OSErrors.add(Version);

		}
		
	}
	

}
