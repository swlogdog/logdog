package logdog.DashBoard.DAO;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Key;

public class CountryReportInfo {
	@PrimaryKey
	@Persistent (valueStrategy = IdGeneratorStrategy.IDENTITY )
	private Key VersionRateKey;
	@Persistent
	String CountryCode;
	@Persistent 
	private int TotalOccurrences;
	public CountryReportInfo(String countryCode) {
		super();
		CountryCode = countryCode;
		TotalOccurrences = 1;
	}
	public Key getVersionRateKey() {
		return VersionRateKey;
	}
	public String getCountryCode() {
		return CountryCode;
	}
	public int getTotalOccurrences() {
		return TotalOccurrences;
	}
	public void UpdatedError()
	{
		TotalOccurrences+=1;
	}
}
