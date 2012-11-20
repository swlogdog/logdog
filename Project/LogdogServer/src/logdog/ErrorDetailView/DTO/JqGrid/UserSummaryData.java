package logdog.ErrorDetailView.DTO.JqGrid;

import java.util.ArrayList;
import java.util.HashMap;

import logdog.Common.Json.jqGridBase;
import logdog.ErrorReport.DTO.UserSummaryInfo;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;

/**
 * 	jqGrid로 UserReport의 기본적인 요약 정보들을 보여주기 위한 객체이다. 
 * @since 2012. 11. 15.오전 7:35:43
 * TODO
 * @author Karuana
 */
public class UserSummaryData extends jqGridBase{
	/**
	 * 	 요약 정보가 들어가 있는 리스트이다.
	 */
	private ArrayList<HashMap<String,String>> summary;
	public UserSummaryData() {
		super();
		summary = new ArrayList<HashMap<String,String>>();
	}

	/**
	 *
	 * @since 2012. 11. 15.오전 7:36:46
	 * TODO
	 * @author Karuana
	 * @param Info
	 * @param datakey
	 */
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
