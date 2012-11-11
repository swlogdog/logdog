package logdog.ErrorReport.DTO;

import javax.jdo.annotations.Persistent;

public class UserSummaryInfo {
	
	private String CountryName;
	private String AppVersion;
	private String OSVersion;
	private int YearCode;
	private int TimeCode;
	
	public UserSummaryInfo(String countryName, String appVersion,
			String oSVersion, int yearCode, int timeCode) {
		super();
		CountryName = countryName;
		AppVersion = appVersion;
		OSVersion = oSVersion;
		YearCode = yearCode;
		TimeCode = timeCode;
	}

	public String getCountryName() {
		return CountryName;
	}

	public String getAppVersion() {
		return AppVersion;
	}

	public String getOSVersion() {
		return OSVersion;
	}

	public int getYearCode() {
		return YearCode;
	}

	public int getTimeCode() {
		return TimeCode;
	}
	
	
}
