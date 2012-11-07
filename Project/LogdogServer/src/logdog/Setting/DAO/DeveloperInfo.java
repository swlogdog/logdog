package logdog.Setting.DAO;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.users.User;

@PersistenceCapable ( identityType = IdentityType.APPLICATION)
public class DeveloperInfo {
	@PrimaryKey
	@Persistent (valueStrategy = IdGeneratorStrategy.IDENTITY )
	private Key DeveloperKey;

	@Persistent 
	private String UserId;// Gmail Id 기준

	public DeveloperInfo(String userId) {
		super();
		UserId = userId;

	}

	public Key getDeveloperKey() {
		return DeveloperKey;
	}
	public String getUserId() {
		return UserId;
	}
	
	
	
}
