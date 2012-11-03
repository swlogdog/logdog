package logdog.Setting.DAO;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Key;

@PersistenceCapable ( identityType = IdentityType.APPLICATION)
public class DeveloperInfo {
	@PrimaryKey
	@Persistent (valueStrategy = IdGeneratorStrategy.IDENTITY )
	private Key DeveloperKey;

	@Persistent 
	private String UserId;// Gmail Id 기준
	@Persistent 
	private int UserRange;
	public DeveloperInfo(String userId, int userRange) {
		super();
		UserId = userId;
		UserRange = userRange;
	}
	public int getUserRange() {
		return UserRange;
	}
	public void setUserRange(int userRange) {
		UserRange = userRange;
	}
	public Key getDeveloperKey() {
		return DeveloperKey;
	}
	public String getUserId() {
		return UserId;
	}
	
	
	
}
