package logdog.Model;

import java.util.Date;


import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import logdog.Util.TimeUtil;

import com.google.appengine.api.blobstore.BlobKey;
import com.google.appengine.api.datastore.Key;
// callStack����� �ȱ�� 
@PersistenceCapable ( identityType = IdentityType.APPLICATION)
public class ErrorClassifiedInfo {
	@PrimaryKey
	@Persistent (valueStrategy = IdGeneratorStrategy.IDENTITY )
	private Key E_ClassificationCode;
	
	@Persistent 
	private String ErrorName;

	@Persistent
	private String OccurrenceClass;
	
	@Persistent 
	private int TotalOccurrences;
	
	@Persistent
	private int WeeklyOccurrences;
	
	@Persistent
	private Date LastUpdateDay;
	
	@Persistent
	private Date WeeklyUpdateDay;
	
	@Persistent
	private boolean BugClear;
	
	@Persistent
	private BlobKey CallstackBlobKey;
	
	
	public ErrorClassifiedInfo(String errorName, String occurrenceClass) {
		super();
		ErrorName = errorName;
		OccurrenceClass = occurrenceClass;
		TotalOccurrences=1;
		WeeklyOccurrences=1;
		LastUpdateDay = TimeUtil.GetNowDate();
		WeeklyUpdateDay =LastUpdateDay;
		BugClear = false;
		CallstackBlobKey=null;
		
	}

	public boolean isBugClear() {
		return BugClear;
	}

	public void setBugClear(boolean bugClear) {
		BugClear = bugClear;
	}

	public Key getE_ClassificationCode() {
		return E_ClassificationCode;
	}

	public String getErrorName() {
		return ErrorName;
	}

	public String getOccurrenceClass() {
		return OccurrenceClass;
	}

	public int getTotalOccurrences() {
		return TotalOccurrences;
	}

	public int getWeeklyOccurrences() {
		return WeeklyOccurrences;
	}

	public Date getLastUpdateDay() {
		return LastUpdateDay;
	}

	public Date getWeeklyUpdateDay() {
		return WeeklyUpdateDay;
	}
	
	public BlobKey getCallstackBlobKey() {
		return CallstackBlobKey;
	}
	
	public void setCallstackBlobKey(BlobKey callstackBlobKey) {
		CallstackBlobKey = callstackBlobKey;
	}

	public boolean updateError()
	{
		try{
			Date AfterWeekTime = TimeUtil.GetTimeWeekAdder(WeeklyUpdateDay);
			LastUpdateDay = TimeUtil.GetNowDate();
			TotalOccurrences+=1;
			
			if(TimeUtil.CheckNowTime(AfterWeekTime))
			{
				WeeklyUpdateDay = LastUpdateDay;
				WeeklyOccurrences=1;
			}
			else{
				WeeklyOccurrences+=1;
			}
	
		}
		catch(Exception e)
		{	
			return false;
		}
		return true;
	}
	

}
