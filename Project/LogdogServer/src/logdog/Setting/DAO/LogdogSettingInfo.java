package logdog.Setting.DAO;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Key;

@PersistenceCapable ( identityType = IdentityType.APPLICATION)
public class LogdogSettingInfo {
	@PrimaryKey
	@Persistent (valueStrategy = IdGeneratorStrategy.IDENTITY )
	private Key LogdogSystemrKey;
	@Persistent 
	private String AppName;
	@Persistent 
	private String DeveloperTeam;
	@Persistent 
	private boolean LogFile;

	public LogdogSettingInfo(String appName, String developerTeam,
			boolean logFile) {
		super();
		AppName = appName;
		DeveloperTeam = developerTeam;
		LogFile = logFile;
	}

	public String getAppName() {
		return AppName;
	}

	public void setAppName(String appName) {
		AppName = appName;
	}

	public String getDeveloperTeam() {
		return DeveloperTeam;
	}

	public void setDeveloperTeam(String developerTeam) {
		DeveloperTeam = developerTeam;
	}

	public boolean isLogFile() {
		return LogFile;
	}

	public void setLogFile(boolean logFile) {
		LogFile = logFile;
	}

	public Key getLogdogSystemrKey() {
		return LogdogSystemrKey;
	}
	

}
