package logdog.DashBoard.DAO;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Key;
@PersistenceCapable ( identityType = IdentityType.APPLICATION)
public class VersionReportInfo {
	@PrimaryKey
	@Persistent (valueStrategy = IdGeneratorStrategy.IDENTITY )
	private Key VersionRateKey;
	@Persistent
	String AppVersion;
	@Persistent
	String OSVersion;
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
