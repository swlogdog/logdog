package logdog.Setting.DAO;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.users.User;

/**
 * 	개발자 리스트를 저장하는 클래스 
 * @since 2012. 11. 19.오전 8:15:12
 * TODO
 * @author Karuana
 */
@PersistenceCapable ( identityType = IdentityType.APPLICATION)
public class DeveloperInfo {
	/**
	 * 개발자 기본 키 
	 */
	@PrimaryKey
	@Persistent (valueStrategy = IdGeneratorStrategy.IDENTITY )
	private Key DeveloperKey;

	/**
	 * 유저 아이디 
	 */
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
