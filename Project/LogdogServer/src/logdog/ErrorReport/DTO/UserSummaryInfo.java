package logdog.ErrorReport.DTO;

import javax.jdo.annotations.Persistent;

/**
 * 유저 리포트 요약 정보 
 * @since 2012. 11. 19.오전 8:06:36
 * TODO
 * @author Karuana
 */
public class UserSummaryInfo {
	
	/**
	 * 국가 코드 
	 */
	private String CountryName;
	/**
	 *  앱 버젼 
	 */
	private String AppVersion;
	/**
	 *  OS 버젼 
	 */
	private String OSVersion;
	/**
	 * 년도 
	 */
	private int YearCode;
	/**
	 * MMDD
	 */
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
