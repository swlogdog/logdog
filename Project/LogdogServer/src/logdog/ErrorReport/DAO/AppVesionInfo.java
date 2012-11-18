package logdog.ErrorReport.DAO;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Key;

/**
 * App버젼 정보를 저장하기 위한 클래스이다.
 * 
 * @since 2012. 11. 19.오전 7:50:55 TODO
 * @author Karuana
 */
@PersistenceCapable(identityType = IdentityType.APPLICATION)
public class AppVesionInfo {
	/**
	 * 기본 키
	 */
	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Key AppKey;

	/**
	 * 버젼
	 */
	@Persistent
	private String Version;

	public AppVesionInfo(String version) {
		super();
		Version = version;
	}

	public Key getAppKey() {
		return AppKey;
	}

	public String getVersion() {
		return Version;
	}

}
