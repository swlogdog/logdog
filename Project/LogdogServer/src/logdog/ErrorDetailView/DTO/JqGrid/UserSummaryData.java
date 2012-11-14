package logdog.ErrorDetailView.DTO.JqGrid;

import java.util.ArrayList;
import java.util.HashMap;

import logdog.Common.Json.jqGridBase;
import logdog.ErrorReport.DTO.UserSummaryInfo;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;

public class UserSummaryData extends jqGridBase{
	private ArrayList<HashMap<String,String>> summary;
	public UserSummaryData() {
		super();
		summary = new ArrayList<HashMap<String,String>>();
	}

	public void addSummaryData(UserSummaryInfo Info, Key datakey)
	{
		HashMap<String,String> data = new HashMap<String,String>();
		int mm = Info.getTimeCode()/100;
		int dd = Info.getTimeCode()%100;
		data.put("date",Info.getYearCode()+"-"+mm+"-"+dd);
		data.put("app", Info.getAppVersion());
		data.put("os", Info.getOSVersion());
		data.put("country", Info.getCountryName());
		data.put("key", KeyFactory.keyToString(datakey));
		summary.add(data);
	}
}
