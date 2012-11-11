package logdog.ErrorReport.DAO.Summary;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Key;

@PersistenceCapable ( identityType = IdentityType.APPLICATION)
public class ClassErrorInfo {
	@PrimaryKey
	@Persistent (valueStrategy = IdGeneratorStrategy.IDENTITY )
	private Key ErrorKey;
	
	@Persistent
	private String OccurrenceClass;
	
	@Persistent 
	private int TotalOccurrences;

	public ClassErrorInfo(){}
	public ClassErrorInfo(String occurrenceClass) {
		super();
		OccurrenceClass = occurrenceClass;
		TotalOccurrences = 1;
	}
	public Key getErrorKey() {
		return ErrorKey;
	}
	public String getOccurrenceClass() {
		return OccurrenceClass;
	}
	public int getTotalOccurrences() {
		return TotalOccurrences;
	}
	
	public void UpdatedError()
	{
		TotalOccurrences+=1;
	}
	
}
