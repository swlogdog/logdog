package logdog.ErrorReport.DAO;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Key;

public class AppVesionInfo {
	@PrimaryKey
	@Persistent (valueStrategy = IdGeneratorStrategy.IDENTITY )
	private Key AppKey;
	
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
