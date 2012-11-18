package logdog.ErrorReport.DAO.Summary;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Key;
/**
 * 국가별 에러 정보를 가지고 있는 DataStore 클래스이다. 미구현 
 * @since 2012. 11. 19.오전 7:43:46
 * TODO
 * @author Karuana
 */
@PersistenceCapable ( identityType = IdentityType.APPLICATION)
public class CountryReportInfo {
	/**
	 * 구분 키 
	 */
	@PrimaryKey
	@Persistent (valueStrategy = IdGeneratorStrategy.IDENTITY )
	private Key VersionRateKey;
	/**
	 * 국가 코드 
	 */
	@Persistent
	String CountryCode;
	/**
	 * 발견 횟수 
	 */
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
