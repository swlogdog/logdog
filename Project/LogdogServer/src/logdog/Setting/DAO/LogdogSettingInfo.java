package logdog.Setting.DAO;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Key;

/**
 * 	로그독 셋팅 정보를 저장하는 객체 
 * @since 2012. 11. 19.오전 8:15:52
 * TODO
 * @author Karuana
 */
@PersistenceCapable ( identityType = IdentityType.APPLICATION)
public class LogdogSettingInfo {
	/**
	 * 기본 키 
	 */
	@PrimaryKey
	@Persistent (valueStrategy = IdGeneratorStrategy.IDENTITY )
	private Key LogdogSystemrKey;
	/**
	 * 로그 파일 전송 여부 
	 */
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
