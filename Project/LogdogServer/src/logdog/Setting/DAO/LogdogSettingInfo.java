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
	private boolean LogFile;

	public LogdogSettingInfo(boolean logFile) {
		super();

		LogFile = logFile;
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
