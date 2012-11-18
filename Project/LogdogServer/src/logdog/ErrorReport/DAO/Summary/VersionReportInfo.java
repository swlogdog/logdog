package logdog.ErrorReport.DAO.Summary;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Key;
/**
 * 	버전별 리포트 정보
 * @since 2012. 11. 19.오전 7:59:31
 * TODO
 * @author Karuana
 */
@PersistenceCapable ( identityType = IdentityType.APPLICATION)
public class VersionReportInfo {
	/**
	 * 기본 키 
	 */
	@PrimaryKey
	@Persistent (valueStrategy = IdGeneratorStrategy.IDENTITY )
	private Key VersionRateKey;
	/**
	 *  앱 버젼 
	 */
	@Persistent
	String AppVersion;
	/**
	 *  OS 버젼 
	 */
	@Persistent
	String OSVersion;
	/**
	 * 발견 횟수 
	 */
	@Persistent 
	private int TotalOccurrences;
	public VersionReportInfo(){}
	public VersionReportInfo(String appVersion, String oSVersion) {
		super();
		AppVersion = appVersion;
		OSVersion = oSVersion;
		TotalOccurrences = 1;
	}
	public void UpdatedError()
	{
		TotalOccurrences+=1;
	}
	public Key getVersionRateKey() {
		return VersionRateKey;
	}

	public String getAppVersion() {
		return AppVersion;
	}

	public String getOSVersion() {
		return OSVersion;
	}

	public int getTotalOccurrences() {
		return TotalOccurrences;
	}
	
	
}
