package logdog.ErrorDetailView.DTO.JqGrid;

import java.util.ArrayList;
import java.util.HashMap;

import logdog.Common.Json.jqGridBase;
import logdog.ErrorReport.DTO.UserReportData;

import com.google.appengine.api.blobstore.BlobKey;

public class UserReportinfo extends jqGridBase{
	private ArrayList<HashMap<String,Object>> userReport;

	public UserReportinfo() {
		super();
		// TODO Auto-generated constructor stub
		userReport = new ArrayList<HashMap<String,Object>> ();
	}

	public void addReport(UserReportData report, BlobKey blob)
	{
		HashMap<String,Object> data = new HashMap<String,Object>();
		data.put("device",report.getDeviceName());
		data.put("gps", report.isGPSState());
		data.put("wifi", report.isWifiState());
		data.put("provider", report.isProviderNetworkState());
		data.put("screan", report.getScreanWidth()+"*"+report.getScreanHeight());
		data.put("blobkey", blob.getKeyString());
		
		userReport.add(data);
	}
}
