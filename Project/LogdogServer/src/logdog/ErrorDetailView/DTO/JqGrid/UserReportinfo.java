package logdog.ErrorDetailView.DTO.JqGrid;

import java.util.ArrayList;
import java.util.HashMap;

import logdog.Common.Json.jqGridBase;
import logdog.ErrorReport.DTO.UserReportData;

import com.google.appengine.api.blobstore.BlobKey;

/**
 * 	jqGrid에서 상세 유저 리포트를 만들어주기 위한 데이터들을 json으로 만들어주기 위한 객체
 * @since 2012. 11. 15.오전 7:28:42
 * TODO
 * @author Karuana
 */
public class UserReportinfo extends jqGridBase{
	/**
	 *  에러리포트 정보를 저장하기 위한 리스트 
	 */
	private ArrayList<HashMap<String,Object>> userReport;

	public UserReportinfo() {
		super();
		// TODO Auto-generated constructor stub
		userReport = new ArrayList<HashMap<String,Object>> ();
	}

	/**
	 *	유저 에러 리포트 객체를 전달 받아 이를 기반으로 Web에 전달할 Json을 구성한다.
	 * @since 2012. 11. 15.오전 7:30:20
	 * TODO
	 * @author Karuana
	 * @param report
	 * @param blob
	 */
	public void addReport(UserReportData report, BlobKey blob)
	{
		HashMap<String,Object> data = new HashMap<String,Object>();
		data.put("index",userReport.size()+1);
		data.put("device",report.getDeviceName());
		data.put("gps", report.isGPSState());
		data.put("wifi", report.isWifiState());
		data.put("provider", report.isProviderNetworkState());
		data.put("screan", report.getScreanWidth()+"*"+report.getScreanHeight());
		if(blob != null)
			data.put("blobkey", blob.getKeyString());
		else
			data.put("blobkey", "null");
		userReport.add(data);
	}
}
